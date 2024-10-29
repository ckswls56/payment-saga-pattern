package pir.stock.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pir.stock.domain.Stock;
import pir.stock.producer.OrderProducer;
import pir.stock.producer.PaymentProducer;
import pir.stock.repository.StockRepository;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {
    private final PaymentProducer paymentProducer;
    private final OrderProducer orderProducer;
    private final StockRepository stockRepository;

    private final WebClient webClient;

    public Mono<Long> getProductIdByOrderService(Long orderId) {
        return webClient.get()
                .uri("/order/{orderId}", orderId)
                .retrieve()
                .bodyToMono(Long.class);
    }

    @Transactional
    public void decrease(Long productId) {
        Stock stock = stockRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));
        if (stock.getStock() <= 0) {
            log.info("{}번 상품 재고 부족", productId);
            throw new IllegalArgumentException("재고가 부족합니다.");
        } else {
            stock.decrease();
            log.info("{}번 상품 재고 -1", productId);
        }
    }

    @Transactional
    public void increase(Long productId) {
        Stock stock = stockRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));
        stock.increase();
        log.info("{}번 상품 재고 +1", productId);
    }

    public void payment(Long orderId) {
        paymentProducer.payment(orderId);
        log.info("{}번 주문 결제", orderId);
    }

    public void rollbackCreatedOrder(Long orderId) {
        orderProducer.rollbackCreatedOrder(orderId);
    }

    public void createStock(Long productId, int amount) {
        Stock stock = new Stock(productId, amount);
        stockRepository.save(stock);
    }
}
