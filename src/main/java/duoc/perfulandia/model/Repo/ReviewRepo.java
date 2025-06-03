package duoc.perfulandia.model.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import duoc.perfulandia.model.Review;
import java.util.List;


public interface  ReviewRepo extends JpaRepository<Review, Long>{
    List<Review> findByClass(Long producId);
}
