package pir.stock.dto;

import lombok.Getter;

@Getter
public class CreateStockDto {
    private Long productId;
    private int amount;
}
