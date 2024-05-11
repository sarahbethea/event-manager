package event.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import event.manager.controller.model.AttendeeData;
import event.manager.service.AttendeeService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/attendee")
@Slf4j
public class AttendeeController {

	
	@Autowired
	AttendeeService attendeeService;
	
	
	@PostMapping("/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	public AttendeeData createAttendee(@RequestBody AttendeeData attendeeData) {
		log.info("Creating new attendee");
		return attendeeService.saveAttendee(attendeeData);
		
	}
	
	@PostMapping("/{attendeeId}/events/{eventId}/add")
	public AttendeeData addAttendeeToEvent(@PathVariable Long attendeeId, 
				@PathVariable Long eventId) {
		log.info("Adding attendee {} to event {}", attendeeId, eventId);
		return attendeeService.addAttendeeToEvent(attendeeId, eventId);
		
	}
	
	@GetMapping("/all")
	public List<AttendeeData> listAllAttendees() {
		log.info("Listing all attendees");
		return attendeeService.retrieveAllAttendees();
	}
	
	@GetMapping("/{attendeeId}")
	public AttendeeData findAttendeeById(@PathVariable Long attendeeId) {
		log.info("Finding attendee with ID=" + attendeeId);
		return attendeeService.retrieveAttendeeById(attendeeId);
		
	}
	
	@PutMapping("/{attendeeId}")
	public AttendeeData updateAttendee(@PathVariable Long attendeeId,
				@RequestBody AttendeeData attendeeData) {
		attendeeData.setAttendeeId(attendeeId);
		log.info("Updating details for attendee {}", attendeeData);
		return attendeeService.saveAttendee(attendeeData);
		
	}
	
	@DeleteMapping("/{attendeeId}")
	public Map<String, String> deleteAttendeeById(@PathVariable Long attendeeId) {
		log.info("Deleting attendee with ID=" + attendeeId);
		attendeeService.deleteAttendeeById(attendeeId);
		
		return Map.of("message", "Deletion of attendee with ID=" + attendeeId + " was successful");
		
	}
	
	@DeleteMapping("/{attendeeId}/events/{eventId}/remove")
	public ResponseEntity<Map<String, String>> removeAttendeeFromEvent(@PathVariable Long attendeeId, 
			@PathVariable Long eventId) {
		log.info("Removing attendee {} from event {}", attendeeId, eventId);
		attendeeService.removeAttendeeFromEvent(attendeeId, eventId);
		
		Map<String, String> responseMessage = Map.of("message", "Attendee with ID=" + attendeeId + 
				" was successfully removed from event with ID=" + eventId);
		
		return ResponseEntity.ok(responseMessage);
		
		
	}
	
}
