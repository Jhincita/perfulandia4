package duoc.perfulandia.controller;
import duoc.perfulandia.model.SupportTicket;
import duoc.perfulandia.model.TicketStatus;
import duoc.perfulandia.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/support")
public class SupportController {
    private final SupportService supportService;

    public SupportController(SupportService supportService) {
        this.supportService = supportService;
    }

    //create

    @PostMapping("/new/{userId}")
    public ResponseEntity<SupportTicket> newSupport(@PathVariable Long userId, @RequestBody SupportTicket ticket) {
        return ResponseEntity.ok(supportService.newTicket(userId, ticket));
    }

    // read
    @GetMapping
    public ResponseEntity<List<SupportTicket>> getAllTickets() {
        return ResponseEntity.ok(supportService.getAllTickets());
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<SupportTicket>> getTicket(@PathVariable Long userId) {
        return ResponseEntity.ok(supportService.getByUser(userId));
    }

    @GetMapping("user/{userId}/open")
    public ResponseEntity<List<SupportTicket>> getOpenTicket(@PathVariable Long userId) {
        return ResponseEntity.ok(supportService.getOpenTickets(userId));
    }

    //update answer
    @PutMapping("/respond/{ticketId}")
    public ResponseEntity<SupportTicket> respondTicket(
            @PathVariable Long ticketId,
            @RequestBody String answer) {
        return ResponseEntity.ok(supportService.answerTicket(ticketId, answer));
    }

    //update close
    @PutMapping("/close/{ticketId}")
    public ResponseEntity<SupportTicket> closeTicket(@PathVariable Long ticketId) {
        return ResponseEntity.ok(supportService.closeTicket(ticketId));
    }

}
