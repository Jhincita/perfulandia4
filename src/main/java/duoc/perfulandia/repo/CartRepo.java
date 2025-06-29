package duoc.perfulandia.repo;

import duoc.perfulandia.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);


}
