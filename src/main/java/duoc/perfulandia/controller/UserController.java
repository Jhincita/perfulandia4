package duoc.perfulandia.controller;

import duoc.perfulandia.model.User;
import duoc.perfulandia.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import duoc.perfulandia.dto.CreateUserDTO;
import duoc.perfulandia.dto.UserDTO;
import duoc.perfulandia.mapper.UserMapper;

import java.util.List;

// metodos actualizados para devolver dto
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserDTO dto) {
        User newUser = userService.createUser(dto);
        return ResponseEntity.ok(userMapper.toDTO(newUser)); // devuelve el userdto, no el user.
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return userService.getUser(id).map(userMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
