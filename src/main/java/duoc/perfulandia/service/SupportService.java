package duoc.perfulandia.service;

import duoc.perfulandia.repo.SupportTicketRepo;
import duoc.perfulandia.repo.UserRepo;
import duoc.perfulandia.model.SupportTicket;
import duoc.perfulandia.model.TicketStatus;
import duoc.perfulandia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SupportService {

    @Autowired
    private SupportTicketRepo ticketRepo;

    @Autowired
    private UserRepo userRepo;

    // create
    public SupportTicket newTicket(Long userId, SupportTicket ticket) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        ticket.setUser(user);
        ticket.setFechaCreacion(LocalDateTime.now());
        ticket.setStatus(TicketStatus.OPEN);
        return ticketRepo.save(ticket);
    }

    // read
    public List<SupportTicket> getByUser(Long userId) {
        return ticketRepo.findByUserId(userId);
    }

    public List<SupportTicket> getAllTickets() {
        return ticketRepo.findAll();
    }

    // read open
    public List<SupportTicket> getOpenTickets(Long userId) {
        return ticketRepo.findByUserIdAndStatus(userId,TicketStatus.OPEN);
    }

    // update
    public SupportTicket answerTicket(Long id, String respuesta) {
        SupportTicket t = ticketRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
        t.setAnswer(respuesta);
        t.setStatus(TicketStatus.IN_PROGRESS);
        return ticketRepo.save(t);
    }

    public SupportTicket closeTicket(Long id) {
        SupportTicket t = ticketRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
        t.setStatus(TicketStatus.CLOSED);
        return ticketRepo.save(t);
    }
}
