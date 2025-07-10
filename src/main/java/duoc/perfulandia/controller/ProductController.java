package duoc.perfulandia.controller;
import duoc.perfulandia.model.Category;
import duoc.perfulandia.model.Product;
import duoc.perfulandia.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import duoc.perfulandia.controller.assembler.ProductModelAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


// HATEOAS implemented, check correct functioning.
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductModelAssembler assembler;

    @Autowired
    public ProductController(ProductService productService, ProductModelAssembler assembler) {
        this.productService = productService;
        this.assembler = assembler;
    }


    // create
    @PostMapping
    public ResponseEntity<EntityModel<Product>> addProduct(@RequestBody Product product) {
        Product newProduct = productService.newProduct(product);
        return ResponseEntity
                .created(linkTo(methodOn(ProductController.class).getProductById(newProduct.getId())).toUri())
                .body(assembler.toModel(newProduct));
    }

    @GetMapping
    public CollectionModel<EntityModel<Product>> getAllProducts() {
        List<EntityModel<Product>> products = productService.getAllProducts().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(products, linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
    }


    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Product>> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(assembler.toModel(product));
    }

    //update
//    @PutMapping("/{id}")
//    public ResponseEntity<EntityModel<Product>> updateProduct(@PathVariable Long id, @RequestBody Product product) {
//        product.setId(id);
//        Product updated = productService.updateProduct(product);
//        return ResponseEntity.ok(assembler.toModel(updated));
//    }

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
