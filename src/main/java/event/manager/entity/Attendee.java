package event.manager.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Attendee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long attendeeId;
	
	private String attendeeFirstName;
	private String attendeeLastName;
	private int attendeeAge;
	private String attendeeEmail;
	
	@ManyToMany(mappedBy = "attendees")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Event> events = new HashSet<>();
	
	@OneToOne
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Ticket ticket;
	
	
	

}
