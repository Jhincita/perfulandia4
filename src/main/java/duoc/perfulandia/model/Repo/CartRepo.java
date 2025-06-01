package duoc.perfulandia.model.Repo;

import duoc.perfulandia.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);


}
