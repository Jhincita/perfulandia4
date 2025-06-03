package duoc.perfulandia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import duoc.perfulandia.model.Product;
import duoc.perfulandia.model.Repo.ProductRepo;
import duoc.perfulandia.model.Repo.ReviewRepo;
import duoc.perfulandia.model.Review;

public class ReviewService {
    @Autowired
    private ReviewRepo reviewRepo;
    @Autowired
    private ProductRepo productRepo;

    public Review addReview(Long productId, Review review) {
        Product product = productRepo.findById(productId);
        if (productOpt.isEmpty()) throw new RuntimeException("Producto no encontrado");

        review.setProduct(productOpt.get());
        return reviewRepo.save(review);
    }
    public List<Review> getReviewsByProduct(Long productId) {
        return reviewRepo.findByProductId(productId);
    }
    public void deleteReview(Long id) {
        reviewRepo.deleteById(id);
    }

}
