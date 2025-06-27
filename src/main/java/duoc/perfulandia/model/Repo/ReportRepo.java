package duoc.perfulandia.model.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import duoc.perfulandia.model.Product;

public interface ReportRepo extends JpaRepository<Product, Long> {
    List<Product> findbyInventorylessThan(int threshold);
}
