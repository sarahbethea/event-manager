package event.manager.controller.model;

import event.manager.entity.Ticket;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketData {
	
	private Long ticketId;
	private Long eventId;
	private Long attendeeId;
	private Double price;
	private String seatNumber;
	

	
	public TicketData(Ticket ticket) {
		ticketId = ticket.getTicketId();
		eventId = ticket.getEvent().getEventId();
		attendeeId = ticket.getAttendee().getAttendeeId();
		price = ticket.getPrice();
		seatNumber = ticket.getSeatNumber();
	}
	
	public  Ticket toTicket() {
		Ticket ticket = new Ticket();
		
		ticket.setTicketId(ticketId);
		ticket.setPrice(price);
		ticket.setSeatNumber(seatNumber);
		
		return ticket;
	
	}
	

}
