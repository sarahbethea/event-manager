package event.manager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import event.manager.entity.Ticket;

public interface TicketDao extends JpaRepository<Ticket, Long> {
//	List<Ticket> findByEventId(Long eventId);
//	List<Ticket> findByAttendeeId(Long attendeeId);
	
}
