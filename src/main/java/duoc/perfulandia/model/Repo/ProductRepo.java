package duoc.perfulandia.model.Repo;

import duoc.perfulandia.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
