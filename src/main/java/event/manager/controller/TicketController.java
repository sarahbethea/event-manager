package event.manager.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import event.manager.controller.model.TicketData;
import event.manager.service.TicketService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ticket")
@Slf4j
public class TicketController {
	
	@Autowired
	TicketService ticketService;
	
	
	
	@PostMapping("/event/{eventId}/attendee/{attendeeId}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public TicketData createTickets(@PathVariable Long eventId, @PathVariable Long attendeeId, 
			@RequestBody TicketData ticketData) {
		log.info("Creating tickets for event with ID=" + eventId);
		return ticketService.saveTickets(eventId, attendeeId, ticketData);
		
		
	}
	
	
	
	@PutMapping("/{ticketId}/attendee/{attendeeId}")
	public TicketData updateTicketDetails(@PathVariable Long ticketId, @PathVariable Long attendeeId, TicketData ticketData) {
		log.info("Updating details for ticket {}" + ticketData);
		return ticketService.saveTickets(ticketId, attendeeId, ticketData);
		
		
	}
	
	@DeleteMapping("/{ticketId}")
	public ResponseEntity<Map<String,String>> deleteTicketById(@PathVariable Long ticketId) {
		log.info("Deleting ticket with ID=" + ticketId);
		ticketService.deleteTicketById(ticketId);
		
		Map<String, String> responseMessage = Map.of("message", "Ticket with ID=" + ticketId + " was deleted");
		
		return ResponseEntity.ok(responseMessage);
		
	}
	

	
	

}
