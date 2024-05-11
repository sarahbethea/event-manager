package event.manager.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import event.manager.controller.model.TicketData;
import event.manager.dao.AttendeeDao;
import event.manager.dao.EventDao;
import event.manager.dao.TicketDao;
import event.manager.entity.Attendee;
import event.manager.entity.Event;
import event.manager.entity.Ticket;

@Service
public class TicketService {
	
	@Autowired
	TicketDao ticketDao;
	
	@Autowired
	EventDao eventDao;
	
	@Autowired
	AttendeeDao attendeeDao;
	
	@Transactional(readOnly = false)
	public TicketData createTickets(TicketData ticketData) {
		Ticket ticket = ticketData.toTicket();
		Ticket dbTicket = ticketDao.save(ticket);
		
		return new TicketData(dbTicket);
		
	}
	
	
	
	
	@Transactional(readOnly = false)
	public TicketData saveTickets(Long eventId, Long attendeeId, TicketData ticketData) {
		Ticket ticket = new Ticket();
		Event event = eventDao.findById(eventId).orElseThrow(() -> 
			new NoSuchElementException("Event with ID=" + eventId + " was not found."));
		Attendee attendee = attendeeDao.findById(attendeeId).orElseThrow(() -> new NoSuchElementException("Attendee not found"));
				
		
		if (Objects.isNull(ticketData.getTicketId())) {
			ticket = new Ticket();
		} else {
			ticket = ticketDao.findById(ticketData.getTicketId()).orElseThrow(() 
					-> new NoSuchElementException("Ticket with ID=" + ticketData.getTicketId() + " was not found."));
		}
		
		ticket.setEvent(event);
		ticket.setSeatNumber(ticketData.getSeatNumber());
		ticket.setPrice(ticketData.getPrice());
		ticket.setAttendee(attendee);
		
		Ticket dbTicket = ticketDao.save(ticket);
		return new TicketData(ticket);
		
	}
	

	private void copyTicketFields(Ticket ticket, TicketData ticketData) {
		ticket.setTicketId(ticketData.getTicketId());
		ticket.setPrice(ticketData.getPrice());
		ticket.setSeatNumber(ticketData.getSeatNumber());
		
	}

	private Ticket findOrCreateTicket(Long ticketId) {
		Ticket ticket = new Ticket();
		
		if(Objects.isNull(ticketId)) {
			ticket = new Ticket();
			
		} else {
			ticket = findTicketById(ticketId);
			
		}
		return ticket;
	}
	
	
	
	

	
	private Ticket findTicketById(Long ticketId) {
		return ticketDao.findById(ticketId).orElseThrow(() ->
				new NoSuchElementException("Ticket with ID=" + ticketId + " was not found"));
	}
	
	@Transactional(readOnly = true)
	public TicketData getTicketById(Long ticketId) {
		Ticket ticket = findTicketById(ticketId);
		return new TicketData(ticket);
		
	}


	@Transactional(readOnly = false)
	public void deleteTicketById(Long ticketId) {
		Ticket ticket = findTicketById(ticketId);
		
		ticketDao.delete(ticket);
		
	}
	
	

}
