package duoc.perfulandia.controller;
import duoc.perfulandia.model.Category;
import duoc.perfulandia.model.Product;
import duoc.perfulandia.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// PROBAR CONTROLLER Y SERVICE::
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // create
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.newProduct(product);
    }

    // read
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    //update
    @PutMapping Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // set category
    @PutMapping("/{productId}/category/{categoryId}")
    public ResponseEntity<Product> assignCategory(
            @PathVariable Long productId,
            @PathVariable Long categoryId) {
        Product updated = productService.setCategory(productId, categoryId);
        return ResponseEntity.ok(updated);
    }

    // set inventory
    @PutMapping("/{productId}/inventory/{quantity}")
    public ResponseEntity<Product> assignInventory(
            @PathVariable Long productId,
            @PathVariable int quantity){
        Product updated = productService.setInventory(productId, quantity);
        return ResponseEntity.ok(updated);
    }

}
