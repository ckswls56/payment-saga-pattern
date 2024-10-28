package pir.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pir.order.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
