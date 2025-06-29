package duoc.perfulandia.dto;

import lombok.Data;

@Data
// se usa el dto para no exponer datos sensiblers (password), etc.
public class UserDTO {
    private Long id;
    private String username;
    private String email;
}
