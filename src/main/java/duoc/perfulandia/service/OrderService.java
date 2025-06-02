package duoc.perfulandia.service;
import duoc.perfulandia.model.*;
import duoc.perfulandia.model.Repo.CartRepo;
import duoc.perfulandia.model.Repo.OrderRepo;
import duoc.perfulandia.model.Repo.ProductRepo;
import duoc.perfulandia.model.Repo.UserRepo;
import duoc.perfulandia.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

// crear ordenes y manejar estados de ordenes

@Service
public class OrderService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private CartService cartService;

    public OrderService(UserRepo userRepo, OrderRepo orderRepo, CartRepo cartRepo, CartService cartService) {
        this.userRepo = userRepo;
        this.orderRepo = orderRepo;
        this.cartRepo = cartRepo;
        this.cartService = cartService;
    }

    // get by userid
    public List<Order> getByUser(Long userId) {
        return orderRepo.findByUser(userRepo.findById(userId));
    }

    public Order getLatestByUserId(String userId) {
        return orderRepo.findTopByUserIdOrderByOrderDateDesc(Long.valueOf(userId));
    }

    // para pagar.
    public List<Order> getPendingByUserId(Long userId) {
        return orderRepo.findByUserIdAndStatus(userId, OrderStatus.PAYMENT_PENDING);
    }


    // 1er paso del checkout: crear orden- > status : PAYMENT_PENDING
    public Order checkoutNewOrder(Long userId){
        // validar user
        Optional<User> userOpt = userRepo.findById(userId);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado con ID: " + userId);
        }
        // get carrito
        Cart selectedCart = cartService.getCartByUserId(userId);
        if (selectedCart.getItems() == null) {
            throw new RuntimeException("El carrito está vacío de USERID: " + userId);
        }
        // generar orden desde carrito
        Order order = new Order();
        order.setUser(userOpt.get());
        order.setOrderDate(LocalDateTime.now());

        List<OrderProduct> orderItems = new ArrayList<>();
        int total = 0;

        for (CartItem cartItem : selectedCart.getItems()) {
            OrderProduct orderItem = new OrderProduct();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
            total += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        order.setOrderProducts(orderItems);
        order.setTotal(total);
        order.setStatus(OrderStatus.PAYMENT_PENDING);

        // vaciar carrito, para uso posterior de otras ordenes.
        // REVISAR SI ES QUE ESTO NO CREA UN CARRITO DUPLICADO(cascade, orphanremoval, etc)
        for (CartItem item : selectedCart.getItems()) {
            item.setCart(null);
        }
        selectedCart.getItems().clear();
        cartRepo.save(selectedCart);
        return orderRepo.save(order);
    }

    // 2do paso checkout: pagar --> STATUS: PROCESSING


}
