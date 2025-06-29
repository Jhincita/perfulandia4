package duoc.perfulandia.mapper;

import duoc.perfulandia.dto.CreateUserDTO;
import duoc.perfulandia.dto.UserDTO;
import duoc.perfulandia.model.User;
import org.springframework.stereotype.Component;

// esta clase mapea los datos del dto a los datos de la clase model USER.
@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public User toEntity(CreateUserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // aqí teóricaemnte esto iría hasheado para seguridad.
        // se hashea para no manejar la contraseña de manera directa
        return user;
    }
}
