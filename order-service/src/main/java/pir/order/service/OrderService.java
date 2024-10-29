package pir.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pir.order.domain.Order;
import pir.order.repository.OrderRepository;
import pir.order.producer.StockProducer;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final StockProducer stockProducer;

    public void order(Long productId){
        final Order order = new Order(productId);
        final Order newOrder = orderRepository.save(order);
        stockProducer.order(newOrder.getId());
        log.info("{}번 주문번호 생성", newOrder.getId());
        log.info("{}번 상품 주문", productId);
    }

    public void delete(Long orderId){
        orderRepository.deleteById(orderId);
        log.info("{}번 주문번호 삭제", orderId);
    }

    public Long getProductId(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(()->new IllegalArgumentException("주문번호가 없습니다."));
        return order.getProductId();
    }
}
