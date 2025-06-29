package duoc.perfulandia.integration;

import duoc.perfulandia.repo.OrderRepo;
import duoc.perfulandia.repo.UserRepo;
import duoc.perfulandia.model.*;
import duoc.perfulandia.service.OrderService;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderServiceIntegrationTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrderRepo orderRepo;

    Faker faker = new Faker();

    @Test
    void testCreateUserGetOrder() {
        // crea usuario falso con datafaker
        User user = new User();
        user.setUsername(faker.name().username());
        userRepo.save(user);

        // crea order falsa para usuario falso
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PAYMENT_PENDING);
        orderRepo.save(order);

        // llama orderservivdce
        List<Order> foundOrders = orderService.getByUser(user.getId());

        // prueba / aseveracion. salio bien o salio mal
        assertFalse(foundOrders.isEmpty());
        assertEquals(order.getStatus(), foundOrders.get(0).getStatus());
        assertEquals(user.getId(), foundOrders.get(0).getUser().getId());
    }
}
