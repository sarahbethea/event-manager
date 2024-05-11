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
import lombok.Value;

@Data
@NoArgsConstructor
public class AttendeeData {
	
	private Long attendeeId;
	private String attendeeFirstName;
	private String attendeeLastName;
	private int attendeeAge;
	private String attendeeEmail;
	private Set<EventResponse> events = new HashSet<>();
	
	
	public AttendeeData(Attendee attendee) {
		attendeeId = attendee.getAttendeeId();
		attendeeFirstName = attendee.getAttendeeFirstName();
		attendeeLastName = attendee.getAttendeeLastName();
		attendeeAge = attendee.getAttendeeAge();
		attendeeEmail = attendee.getAttendeeEmail();
		
		for (Event event : attendee.getEvents()) {
			events.add(new EventResponse(event));
			
		}
		
	}
	
	public Attendee toAttendee() {
		Attendee attendee = new Attendee();
		
		attendee.setAttendeeId(attendeeId);
		attendee.setAttendeeFirstName(attendeeFirstName);
		attendee.setAttendeeLastName(attendeeLastName);
		attendee.setAttendeeAge(attendeeAge);
		attendee.setAttendeeEmail(attendeeEmail);
		
		return attendee;
	}
	
	
	
	
	@Value
	static class EventResponse {
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
		
		
		public EventResponse(Event event) {
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
			
			
			
		}
	
	}
	
	
}
