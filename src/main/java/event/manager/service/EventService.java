package event.manager.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import event.manager.controller.model.EventData;
import event.manager.dao.CategoryDao;
import event.manager.dao.EventDao;
import event.manager.entity.Category;
import event.manager.entity.Event;

@Service
public class EventService {
	
	@Autowired
	private EventDao eventDao;
	
	@Autowired
	private CategoryDao categoryDao;
	

	
	@Transactional(readOnly = false)
	public EventData saveEvent(EventData eventData) {
		
		Event event = eventData.toEvent();
		Event dbEvent = eventDao.save(event);
		
		return new EventData(dbEvent);
		
		
	}
	
	private Event findOrCreateEvent(Long eventId) {
		Event event;
		
		if(Objects.isNull(eventId)) {
			event = new Event();
		} else {
			event = findEventById(eventId);
		}
		return event;
	}

	@Transactional(readOnly = true)
	public List<EventData> retrieveAllEvents() {
		List<Event> events = eventDao.findAll();
		List<EventData> result = new LinkedList<>();
		
		for (Event event : events) {
			EventData ed = new EventData(event);
			
			ed.getAttendees().clear();
			ed.getTickets().clear();
			ed.getCategories().clear();
			
			result.add(ed);
		}
		
		return result;
	}

	@Transactional(readOnly = true)
	public EventData retrieveEventById(Long eventId) {
		Event event = findEventById(eventId);
		return new EventData(event);
		
	}

	private Event findEventById(Long eventId) {
		return eventDao.findById(eventId).orElseThrow(() ->
				new NoSuchElementException("Event with ID=" + eventId + " does not exist"));
		
	}

	@Transactional(readOnly = false)
	public void deleteEventById(Long eventId) {
		Event event = findEventById(eventId);
		eventDao.delete(event);
		
	}
	
	public void copyEventFields(Event event, EventData eventData) {
		event.setEventId(eventData.getEventId());
		event.setEventName(eventData.getEventName());
		event.setEventDate(eventData.getEventDate());
		event.setEventAddress(eventData.getEventAddress());
		event.setCity(eventData.getCity());
		event.setState(eventData.getState());
		event.setZipcode(eventData.getZipcode());
		event.setAvailableTickets(eventData.getAvailableTickets());
		event.setTicketsSold(eventData.getTicketsSold());
	}
	

	

}
