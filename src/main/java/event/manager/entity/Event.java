package event.manager.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long eventId;
	
	private String eventName;
	private String eventDate;
	private String eventAddress;
	private String city;
	private String state;
	private String zipcode;
	private int availableTickets;
	private int ticketsSold;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "event_attendee",
		joinColumns = @JoinColumn(name = "event_id"),
		inverseJoinColumns = @JoinColumn(name = "attendee_id"))
	private Set<Attendee> attendees = new HashSet<>();
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "event_category",
		joinColumns = @JoinColumn(name = "event_id"),
		inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Ticket> tickets = new HashSet<>();
	
	
	
	
	

}
