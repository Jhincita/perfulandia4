package duoc.perfulandia.mapper;


import duoc.perfulandia.dto.AnswerRequestDTO;
import duoc.perfulandia.model.TicketResponse;
import org.springframework.stereotype.Component;

// esta clase mapea los datos del dto a los datos de la clase model USER.
@Component
public class TicketResponseMapper {

    public static TicketResponse toEntity(AnswerRequestDTO dto) {
        TicketResponse response = new TicketResponse();
        response.setMessage(dto.getMessage());
        return response;
    }

    public static AnswerRequestDTO toDTO(TicketResponse entity) {
        AnswerRequestDTO dto = new AnswerRequestDTO();
        dto.setMessage(entity.getMessage());
        dto.setEmployeeId(entity.getEmployee().getId());
        return dto;
    }
}
