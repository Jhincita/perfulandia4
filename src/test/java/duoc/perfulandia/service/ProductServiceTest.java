package duoc.perfulandia.service;

import duoc.perfulandia.model.*;
import duoc.perfulandia.repo.CategoryRepo;
import duoc.perfulandia.repo.OrderRepo;
import duoc.perfulandia.repo.ProductRepo;
import duoc.perfulandia.repo.UserRepo;
import net.datafaker.Faker;
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
public class ProductServiceTest {
    @Mock
    private ProductRepo productRepo;
    @Mock
    private CategoryRepo categoryRepo;

    @InjectMocks
    private ProductService productService;

    private Faker faker;
    private Product sampleProduct;
    private Category sampleCategory;

    @BeforeEach
    void setUp() {
        faker = new Faker();

        sampleCategory = new Category();
        sampleCategory.setId(faker.number().randomNumber());
        sampleCategory.setName(faker.commerce().department());

        sampleProduct = new Product();
        sampleProduct.setId(faker.number().randomNumber());
        sampleProduct.setName(faker.commerce().productName());
        sampleProduct.setBrand(faker.company().name());
        sampleProduct.setNotes(faker.lorem().sentence());
        sampleProduct.setPrice(faker.number().numberBetween(10, 400));
        sampleProduct.setMl(faker.number().numberBetween(10, 200));
        sampleProduct.setCategory(sampleCategory);
        sampleProduct.setInventory(faker.number().numberBetween(1, 200));
    }

    @Test
    void newProduct_savesAndReturnsCorrectProduct() {
        when(productRepo.save(sampleProduct)).thenReturn(sampleProduct);
        Product savedProduct = productService.newProduct(sampleProduct);
        assertNotNull(savedProduct);
        assertEquals(sampleProduct.getName(), savedProduct.getName());
        assertEquals(sampleProduct.getBrand(), savedProduct.getBrand());
        assertEquals(sampleProduct.getCategory(), savedProduct.getCategory());
        assertEquals(sampleProduct.getNotes(), savedProduct.getNotes());
        assertEquals(sampleProduct.getPrice(), savedProduct.getPrice());
        assertEquals(sampleProduct.getMl(), savedProduct.getMl());
        assertEquals(sampleProduct.getInventory(), savedProduct.getInventory());
        verify(productRepo).save(sampleProduct);
    }

    @Test
    void getProduct_returnsCorrectProduct() {
        when(productRepo.findById(sampleProduct.getId())).thenReturn(Optional.of(sampleProduct));
        Optional<Product> productOptional = productService.getProduct(sampleProduct.getId());
        assertTrue(productOptional.isPresent());
        assertEquals(sampleProduct.getName(), productOptional.get().getName());
        assertEquals(sampleProduct.getBrand(), productOptional.get().getBrand());
        assertEquals(sampleProduct.getCategory(), productOptional.get().getCategory());
        assertEquals(sampleProduct.getNotes(), productOptional.get().getNotes());
        assertEquals(sampleProduct.getPrice(), productOptional.get().getPrice());
        assertEquals(sampleProduct.getMl(), productOptional.get().getMl());
        assertEquals(sampleProduct.getInventory(), productOptional.get().getInventory());
        verify(productRepo).findById(sampleProduct.getId());
    }

    @Test
    void getAllProducts_returnsAllProducts() {
        when(productRepo.findAll()).thenReturn(List.of(sampleProduct));
        List<Product> products = productService.getAllProducts();
        assertNotNull(products);
        verify(productRepo).findAll();
    }



}
