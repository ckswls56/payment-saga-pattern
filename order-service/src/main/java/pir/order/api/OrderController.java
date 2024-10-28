package pir.order.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pir.order.service.OrderService;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order")
    public void order(@RequestParam Long productId){
        orderService.order(productId);
    }
    @GetMapping("/order/{orderId}")
    public Long getProductId(@PathVariable Long orderId){
        return orderService.getProductId(orderId);
    }

}
