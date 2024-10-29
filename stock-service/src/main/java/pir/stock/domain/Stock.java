package pir.stock.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "stocks")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class Stock {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false, updatable = false, unique = true)
        private Long id;
        @Column(name = "product_id", nullable = false)
        private Long productId;
        @Column(name = "stock")
        private int stock;

        public Stock(Long productId, int stock) {
            this.productId = productId;
            this.stock = stock;
        }
        public void decrease(){
            this.stock--;
        }
        public void increase(){
            this.stock++;
        }
}
