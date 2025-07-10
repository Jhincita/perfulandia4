package duoc.perfulandia.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank(message = "Campo email obligatorio")
    @Email(message = "El email debe tener formato user@mail.com")
    private String email;
    @NotBlank(message = "Campo contraseña obligatorio")
    private String password;

}
