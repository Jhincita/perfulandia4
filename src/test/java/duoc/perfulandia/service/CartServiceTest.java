package duoc.perfulandia.service;

import duoc.perfulandia.model.*;
import duoc.perfulandia.repo.CartRepo;
import duoc.perfulandia.repo.OrderRepo;
import duoc.perfulandia.repo.ProductRepo;
import duoc.perfulandia.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    private UserRepo userRepo;
    @Mock
    private OrderRepo orderRepo;
    @Mock
    private CartRepo cartRepo;
    @Mock
    private ProductRepo productRepo;

    @InjectMocks
    private CartService cartService;

    private User sampleUser;
    private Cart sampleCart;
    private Product sampleProduct;
    private CartItem sampleCartItem;
    private Faker faker;

    @BeforeEach
    public void setUp() {
        faker = new Faker();

        sampleUser = new User();
        sampleUser.setId(faker.number().randomNumber());
        sampleUser.setUsername(faker.name().username());
        sampleUser.setEmail(faker.internet().emailAddress());

        sampleProduct = new Product();
        sampleProduct.setId(faker.number().randomNumber());
        sampleProduct.setName(faker.commerce().productName());
        sampleProduct.setPrice(faker.number().numberBetween(10, 100));
        sampleProduct.setInventory(faker.number().numberBetween(1, 50));

        sampleCartItem = new CartItem();
        sampleCartItem.setId(faker.number().randomNumber());
        sampleCartItem.setProduct(sampleProduct);
        sampleCartItem.setQuantity(faker.number().numberBetween(1, 5));

        sampleCart = new Cart();
        sampleCart.setId(faker.number().randomNumber());
        sampleCart.setUser(sampleUser);
        sampleCart.setItems(new ArrayList<>());
        sampleCart.getItems().add(sampleCartItem);
    }

    @Test
    void createCart_createsCart() {
        when(userRepo.findById(sampleUser.getId())).thenReturn(Optional.of(sampleUser));
        when(cartRepo.findByUserId(sampleUser.getId())).thenReturn(null);
        when(cartRepo.save(any(Cart.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Cart newCart = cartService.createCart(sampleUser.getId());

        assertNotNull(newCart);
        assertEquals(sampleUser.getId(), newCart.getUser().getId());
        assertTrue(newCart.getItems().isEmpty());

        verify(userRepo).findById(sampleUser.getId());
        verify(cartRepo).findByUserId(sampleUser.getId());
        verify(cartRepo).save(any(Cart.class));
    }

    @Test
    void getCart_returnsCart() {
        when(cartRepo.findByUserId(sampleUser.getId())).thenReturn(sampleCart);

        Cart foundCart = cartService.getCartByUserId(sampleUser.getId());
        assertNotNull(foundCart); // hay carrito
        assertEquals(sampleUser.getId(), foundCart.getUser().getId());

        verify(cartRepo).findByUserId(sampleUser.getId());
    }


}