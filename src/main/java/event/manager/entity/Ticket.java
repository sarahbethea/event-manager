package event.manager.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ticketId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JoinColumn(name = "event_id", nullable = true)
	private Event event;
	
	@OneToOne(cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JoinColumn(name = "attendee_id", nullable = true)
	private Attendee attendee;
	
	private Double price;
	private String seatNumber;
	

	

}
