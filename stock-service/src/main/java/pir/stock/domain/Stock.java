package pir.stock.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "stocks")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false, updatable = false, unique = true)
        private Long id;
        @Column(name = "order_id", nullable = false)
        private Long orderId;
        @Column(name = "stock")
        private int stock;

        public Stock(Long orderId, int stock) {
            this.orderId = orderId;
            this.stock = stock;
        }
        public void decrease(){
            this.stock--;
        }
        public void increase(){
            this.stock++;
        }
}
