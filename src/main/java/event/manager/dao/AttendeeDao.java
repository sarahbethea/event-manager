package event.manager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import event.manager.entity.Attendee;

public interface AttendeeDao extends JpaRepository<Attendee, Long> {

}
