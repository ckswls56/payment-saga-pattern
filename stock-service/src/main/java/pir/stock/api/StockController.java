package pir.stock.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pir.stock.dto.CreateStockDto;
import pir.stock.service.StockService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stock")
public class StockController {
    private final StockService stockService;

    @PostMapping("")
    public void createStock(@RequestBody CreateStockDto createStockDto){
        stockService.createStock(createStockDto.getProductId(), createStockDto.getAmount());
    }

}
