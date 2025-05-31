package duoc.perfulandia.service;
import duoc.perfulandia.model.Repo.CategoryRepo;
import duoc.perfulandia.model.Repo.ProductRepo;
import duoc.perfulandia.model.Product;
import duoc.perfulandia.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    public ProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    // CRUD
    // create
    public Product newProduct(Product product) {
        return productRepo.save(product);
    }

    // read
    public Optional<Product> getProduct(Long id) {
        return productRepo.findById(id);
    }
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
    // update. make update by id ? -> no bc updateproduct(getproduct(id))

    public Product updateProduct(Product updatedProduct) {
        Product existingProduct = productRepo.findById(updatedProduct.getId())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + updatedProduct.getId()));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setBrand(updatedProduct.getBrand());
        existingProduct.setNotes(updatedProduct.getNotes());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setMl(updatedProduct.getMl());
        existingProduct.setCategory(updatedProduct.getCategory());

        return productRepo.save(existingProduct);
    }


    // delete
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

    // set category
    public Product setCategory(Long productId, Long categoryId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + productId));

        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + categoryId));

        product.setCategory(category);
        return productRepo.save(product);
    }

    //set inventory
    public Product setInventory(Long productId, int quantity) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        product.setInventory(quantity);
        return productRepo.save(product);
    }


}
