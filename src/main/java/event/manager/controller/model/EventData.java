package event.manager.controller.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import event.manager.entity.Attendee;
import event.manager.entity.Category;
import event.manager.entity.Event;
import event.manager.entity.Ticket;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventData {
	
	private Long eventId;
	private String eventName;
	private String eventDate;
	private String eventAddress;
	private String city;
	private String state;
	private String zipcode;
	private int availableTickets;
	private int ticketsSold;
	private Set<CategoryData> categories = new HashSet<>();
	private Set<TicketData> tickets = new HashSet<>();
	private Set<AttendeeData> attendees = new HashSet<>();
	
	
	
	public EventData(Event event) {
		eventId = event.getEventId();
		eventName = event.getEventName();
		eventDate = event.getEventDate();
		eventAddress = event.getEventAddress();
		city = event.getCity();
		state = event.getState();
		zipcode = event.getZipcode();
		availableTickets = event.getAvailableTickets();
		ticketsSold = event.getTicketsSold();
		
		for (Category category : event.getCategories()) {
			categories.add(new CategoryData(category));
	
		}
		
		for (Ticket ticket : event.getTickets()) {
			tickets.add(new TicketData(ticket));
			
		}
		
		for (Attendee attendee: event.getAttendees()) {
			attendees.add(new AttendeeData(attendee));
			
		}
		
		
	}
	
	public Event toEvent() {
		Event event = new Event();
		
		event.setEventId(eventId);
		event.setEventName(eventName);
		event.setEventDate(eventDate);
		event.setEventAddress(eventAddress);
		event.setCity(city);
		event.setState(state);
		event.setZipcode(zipcode);
		event.setAvailableTickets(availableTickets);
		event.setAvailableTickets(availableTickets);
		
		return event;
	}


}
