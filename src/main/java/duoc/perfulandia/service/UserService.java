package duoc.perfulandia.service;
import duoc.perfulandia.model.Cart;
import duoc.perfulandia.model.Repo.CartRepo;
import duoc.perfulandia.model.Repo.UserRepo;
import duoc.perfulandia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final CartRepo cartRepo;

    public UserService(UserRepo userRepo, CartRepo cartRepo) {
        this.userRepo = userRepo;
        this.cartRepo = cartRepo;
    }
    // CRUD
    // create
    public User createUser(User user) {
        User savedUser = userRepo.save(user);

        Cart cart = new Cart();
        cart.setUser(savedUser);
        cart.setItems(new ArrayList<>());
        cartRepo.save(cart);
        return savedUser;
    }

    // read
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    public Optional<User> getUser(Long id) {
        return userRepo.findById(id);
    }
    // update
    public User updateUser(User user) {
        return userRepo.save(user);
    }
    // delete
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

}
