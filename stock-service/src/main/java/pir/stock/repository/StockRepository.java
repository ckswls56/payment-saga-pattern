package pir.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pir.stock.domain.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
