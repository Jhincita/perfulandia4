package duoc.perfulandia.model.Repo;

import duoc.perfulandia.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
