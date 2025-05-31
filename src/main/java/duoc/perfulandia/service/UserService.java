package duoc.perfulandia.service;

import duoc.perfulandia.model.Repo.UserRepo;
import duoc.perfulandia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    // CRUD
    // create
    public User createUser(User user) {
        return userRepo.save(user);
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
