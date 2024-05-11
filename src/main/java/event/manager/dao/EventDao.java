package event.manager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import event.manager.entity.Event;

public interface EventDao extends JpaRepository<Event, Long> {

}
