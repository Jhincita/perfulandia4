package duoc.perfulandia.repo;

import duoc.perfulandia.model.TicketResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketResponseRepo extends JpaRepository<TicketResponse, Long> {
    List<TicketResponse> findByTicketId(Long ticketId);
}
