package duoc.perfulandia.model.Repo;

import duoc.perfulandia.model.Order;
import duoc.perfulandia.model.OrderStatus;
import duoc.perfulandia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order, Long> {
    Order findTopByUserIdOrderByOrderDateDesc(Long aLong);
    List<Order> findByUserIdAndStatus(Long userId, OrderStatus status);

    Optional<Order> findFirstByUserIdAndStatus(Long userId, OrderStatus status);


    List<Order> findByUser(Optional<User> user);
}
