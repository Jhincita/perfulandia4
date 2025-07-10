package duoc.perfulandia.controller;

import duoc.perfulandia.dto.LoginDTO;
import duoc.perfulandia.model.User;
import duoc.perfulandia.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO.getEmail(), loginDTO.getPassword())
                .map(user -> ResponseEntity.ok("Login successful for: " + user.getEmail()))
                .orElseGet(() -> ResponseEntity.status(401).body("Invalid email or password"));
    }
}
