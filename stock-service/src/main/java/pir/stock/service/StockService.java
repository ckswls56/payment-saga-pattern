package pir.stock.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pir.stock.domain.Stock;
import pir.stock.producer.OrderProducer;
import pir.stock.producer.PaymentProducer;
import pir.stock.repository.StockRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {
    private final PaymentProducer paymentProducer;
    private final OrderProducer orderProducer;
    private final StockRepository stockRepository;

    public void decrease(Long orderId){
        Stock stock = stockRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));
        if (stock.getStock() <= 0){
            log.info("{}번 상품 재고 부족", orderId);
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        else {
            stock.decrease();
            log.info("{}번 상품 재고 -1", orderId);
        }
    }

    public void increase(Long orderId){
        Stock stock = stockRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));
        stock.increase();
        log.info("{}번 상품 재고 +1", orderId);
    }

    public void payment(Long orderId){
        paymentProducer.payment(orderId);
    }

    public void rollbackCreatedOrder(Long orderId){
        orderProducer.rollbackCreatedOrder(orderId);
    }

    public void createStock(Long orderId, int amount){
        Stock stock = new Stock(orderId, amount);
        stockRepository.save(stock);
    }
}
