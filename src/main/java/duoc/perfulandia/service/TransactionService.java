package duoc.perfulandia.service;
import duoc.perfulandia.model.Order;
import duoc.perfulandia.model.OrderStatus;
import duoc.perfulandia.model.Repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

// manejar transacciones de cualquier tipo
@Service
public class TransactionService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderService orderService;

    public Order payPendingOrderByUserId(Long userId) {
        Order order = orderRepo.findFirstByUserIdAndStatus(userId, OrderStatus.PAYMENT_PENDING)
                .orElseThrow(() -> new RuntimeException("No se encontraron ordenes por pagar para USERID: " + userId));

        order.setStatus(OrderStatus.PROCESSING);
        order.setPaymentDate(LocalDateTime.now());
        order.setTotalPaid(order.getTotal());

        return orderRepo.save(order);
    }



}
