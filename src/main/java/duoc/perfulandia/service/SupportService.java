package duoc.perfulandia.service;

import duoc.perfulandia.dto.AnswerRequestDTO;
import duoc.perfulandia.mapper.TicketResponseMapper;
import duoc.perfulandia.model.*;
import duoc.perfulandia.repo.EmployeeRepo;
import duoc.perfulandia.repo.SupportTicketRepo;
import duoc.perfulandia.repo.TicketResponseRepo;
import duoc.perfulandia.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class SupportService {

    @Autowired
    private SupportTicketRepo ticketRepo;
    @Autowired
    private CustomerRepo userRepo;
    @Autowired
    private TicketResponseRepo responseRepo;
    @Autowired
    private EmployeeRepo employeeRepo;

    // create
    public SupportTicket newTicket(Long userId, SupportTicket ticket) {
        Customer user = userRepo.findById(userId)
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

    // responder ticket
    public TicketResponse answerTicket(Long ticketId, AnswerRequestDTO dto) {
        SupportTicket ticket = ticketRepo.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));

        Employee empleado = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        TicketResponse response = TicketResponseMapper.toEntity(dto);
        response.setTicket(ticket);
        response.setEmployee(empleado);
        response.setFechaRespuesta(LocalDateTime.now());

        ticket.setStatus(TicketStatus.IN_PROGRESS);
        ticketRepo.save(ticket);

        return responseRepo.save(response);
    }

    public SupportTicket closeTicket(Long id) {
        SupportTicket t = ticketRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
        t.setStatus(TicketStatus.CLOSED);
        return ticketRepo.save(t);
    }

}
