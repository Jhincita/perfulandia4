package duoc.perfulandia.controller;

import duoc.perfulandia.model.Order;
import duoc.perfulandia.service.ShippingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ship")
public class ShippingController {
    private final ShippingService shippingService;

    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<Order> shipOrder(@PathVariable Long userId){
        Order order = shippingService.shipOrder(userId);
        return ResponseEntity.ok(order);
    }

}
