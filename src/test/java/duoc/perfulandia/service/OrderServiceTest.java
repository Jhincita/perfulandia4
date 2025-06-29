package duoc.perfulandia.service;

import duoc.perfulandia.model.*;
import duoc.perfulandia.repo.OrderRepo;
import duoc.perfulandia.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepo orderRepo;
    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private OrderService orderService;

    private User sampleUser;
    private Order sampleOrder;

    @BeforeEach
    void setUp() {
        sampleUser = new User();
        sampleUser.setId(1L);
        sampleUser.setUsername("elzapato");

        sampleOrder = new Order();
        sampleOrder.setId(1L);
        sampleOrder.setUser(sampleUser);
        sampleOrder.setStatus(OrderStatus.PAYMENT_PENDING);
    }

    @Test
    void OrderService_getByUser_returnsOrder() {
        // buscar data
        Optional<User> optionalUser = Optional.of(sampleUser);
        List<Order> sampleOrders = List.of(sampleOrder);  // assuming sampleOrder is defined

        // encuentra el optionaluser
        when(userRepo.findById(sampleUser.getId())).thenReturn(optionalUser);

        // devuelve lista de ordenes. !! importante porque el metodo original del orderservice devuelve list
        when(orderRepo.findByUser(optionalUser)).thenReturn(sampleOrders);

        // llama al orderservice
        List<Order> orders = orderService.getByUser(sampleUser.getId());

        // assertions --> salio bien o salio mal.
        assertFalse(orders.isEmpty());
        assertEquals(sampleOrder.getId(), orders.get(0).getId());
    }

    @Test
    void orderService_getByOrderId_returnsOrder() {
        when(orderRepo.findTopByUserIdOrderByOrderDateDesc(1L))
                .thenReturn(sampleOrder);

        Order result = orderService.getLatestByUserId(1L);

        assertNotNull(result);
        assertEquals(sampleOrder.getId(), result.getId());
        verify(orderRepo).findTopByUserIdOrderByOrderDateDesc(1L);
    }

    @Test
    void orderService_getByOrderStatus_returnsOrder() {
        when(orderRepo.findByUserIdAndStatus(1L, OrderStatus.PAYMENT_PENDING))
                .thenReturn(List.of(sampleOrder));

        List<Order> result = orderService.getPendingByUserId(1L);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(OrderStatus.PAYMENT_PENDING, result.get(0).getStatus());
        verify(orderRepo).findByUserIdAndStatus(1L, OrderStatus.PAYMENT_PENDING);
    }


    // falta.
    @Test
    void orderService_checkOutNewOrder_returnsCheckedOutOrder() {}


}