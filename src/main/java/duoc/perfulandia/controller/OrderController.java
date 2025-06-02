package duoc.perfulandia.controller;

import duoc.perfulandia.model.Order;
import duoc.perfulandia.service.OrderService;
import duoc.perfulandia.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final TransactionService transactionService;

    public OrderController(OrderService orderService, TransactionService transactionService) {
        this.orderService = orderService;
        this.transactionService = transactionService;
    }

    // get todas by userid
    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getOrders(@PathVariable Long userId) {
        List<Order> orders = orderService.getByUser(userId);
        return ResponseEntity.ok(orders);
    }

    // get ordenes pago pendiente
    @GetMapping("/pending/{userId}")
    public ResponseEntity<List<Order>> getPendingOrders(@PathVariable Long userId) {
        List<Order> pendingOrders = orderService.getPendingByUserId(userId);
        return ResponseEntity.ok(pendingOrders);
    }

    @PostMapping("/checkout/{userId}")
    public ResponseEntity<Order> createOrder(@PathVariable Long userId) {
        Order newOrder = orderService.checkoutNewOrder(userId);
        return ResponseEntity.ok(newOrder);
    }

    @PostMapping("/pay/{userId}")
    public ResponseEntity<Order> payPendingOrder(@PathVariable Long userId) {
        try {
            Order paidOrder = transactionService.payPendingOrderByUserId(userId);
            return ResponseEntity.ok(paidOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


}
