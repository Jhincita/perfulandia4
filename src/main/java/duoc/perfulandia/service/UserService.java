package duoc.perfulandia.service;
import duoc.perfulandia.dto.CreateUserDTO;
import duoc.perfulandia.dto.UserDTO;
import duoc.perfulandia.mapper.UserMapper;
import duoc.perfulandia.model.Cart;
import duoc.perfulandia.repo.CartRepo;
import duoc.perfulandia.repo.UserRepo;
import duoc.perfulandia.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserRepo userRepo;
    private final CartRepo cartRepo;
    private final UserMapper userMapper;



    public UserService(UserRepo userRepo, CartRepo cartRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.cartRepo = cartRepo;
        this.userMapper = userMapper;
    }
    // CRUD
    // create
    public User createUser(CreateUserDTO dto) {
        // hashear pass:
        User user = userMapper.toEntity(dto);
        String hashedPassword = passwordEncoder.encode(dto.getPassword());
        user.setPassword(hashedPassword);
        // save new user
        User newUser = userRepo.save(user);
// asignar un nuevo carrrito al new user
        Cart cart = new Cart();
        cart.setUser(newUser);
        cart.setItems(new ArrayList<>());
        cartRepo.save(cart);

        return newUser;
    }



    // read
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        for (User user : userRepo.findAll()) {
            users.add(userMapper.toDTO(user));
        }
        return users;
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
