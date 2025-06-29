package duoc.perfulandia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
// para crear user. para no exponer datos sensibles como la password
public class CreateUserDTO {
    @NotBlank
    private String username;

    @Email
    private String email;

    @NotBlank
    private String password;
}
