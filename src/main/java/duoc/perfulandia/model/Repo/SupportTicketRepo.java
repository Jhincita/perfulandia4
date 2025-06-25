package duoc.perfulandia.model.Repo;

import duoc.perfulandia.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupportTicketRepo extends JpaRepository<SupportTicket, Long> {
    List<SupportTicket> findByUserId(Long userId);

    SupportTicket findTopByUserIdOrderByFechaCreacionDesc(Long userId);
    List<SupportTicket> findByUserIdAndStatus(Long userId, TicketStatus status);

    Optional<SupportTicket> findFirstByUserIdAndStatus(Long userId, TicketStatus status);

}
