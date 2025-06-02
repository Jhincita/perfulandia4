package duoc.perfulandia.service;

import duoc.perfulandia.model.Order;
import duoc.perfulandia.model.OrderStatus;
import duoc.perfulandia.model.Repo.OrderRepo;
import duoc.perfulandia.model.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShippingService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserRepo userRepo;

    // get all orders with status: processing
    public List<Order> getAllProcessingOrders(){
        return orderRepo.findByStatus(OrderStatus.PROCESSING);
    }

    // get first order by userid by status processing (para citar en shipping method )
    public Optional<Order> getOrderForShippingByUserId(Long userId){
        return orderRepo.findFirstByUserIdAndStatus(userId, OrderStatus.PROCESSING);
    }

    // shipping method
    public Order shipOrder(Long userId){
        // encontrar orden por userid
        Optional<Order> shippedOrderOpt = getOrderForShippingByUserId(userId);
        if (shippedOrderOpt.isEmpty()) {
            throw new RuntimeException("No se encontraron órdenes listas para envío de USERID: " + userId);
        }

        Order order = shippedOrderOpt.get();
        order.setStatus(OrderStatus.SHIPPED);

        return orderRepo.save(order);
    }
}
