package event.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import event.manager.controller.model.EventData;
import event.manager.service.EventService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/event")
@Slf4j
public class EventController {

	@Autowired
	private EventService eventService;
	
	
	
	@GetMapping("/all")
	public List<EventData> listAllEvents() {
		log.info("Listing all events");
		return eventService.retrieveAllEvents();
	}
	
	@GetMapping("/{eventId}")
	public EventData findEventById(@PathVariable Long eventId) {
		log.info("Finding event with ID=" + eventId);
		return eventService.retrieveEventById(eventId);
		
	}
	
	
	@PostMapping("/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	public EventData createEvent(@RequestBody EventData eventData) {
		log.info("Creating new event {}", eventData);
		return eventService.saveEvent(eventData);
		
	}
	
	
	
	@PutMapping("/update/{eventId}")
	public EventData updateEvent(@PathVariable Long eventId,
			@RequestBody EventData eventData) {
		eventData.setEventId(eventId);
		log.info("Updating event {}", eventData);
		return eventService.saveEvent(eventData);
		
	}
	
	@DeleteMapping("/{eventId}") 
	public Map<String, String> deleteEventById(@PathVariable Long eventId) {
		log.info("Deleting event with ID=" + eventId);
		eventService.deleteEventById(eventId);
		
		return Map.of("message", "Deletion of event with ID=" + eventId + " was successful.");
		
	}
	
	
	
	
	
	
	
	
	
	
}
