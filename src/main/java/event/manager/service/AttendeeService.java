package event.manager.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import event.manager.controller.model.AttendeeData;
import event.manager.dao.AttendeeDao;
import event.manager.dao.EventDao;
import event.manager.entity.Attendee;
import event.manager.entity.Event;

@Service
public class AttendeeService {
	
	@Autowired
	private AttendeeDao attendeeDao;
	
	@Autowired
	private EventDao eventDao;
	

	@Transactional(readOnly = false)
	public AttendeeData saveAttendee(AttendeeData attendeeData) {
		
		Attendee attendee = attendeeData.toAttendee();
		Attendee dbAttendee = attendeeDao.save(attendee);
		
		return new AttendeeData(dbAttendee);

		
	}
	
	
	public Attendee findOrCreateAttendee(Long attendeeId) {
		
		Attendee attendee = new Attendee();
		
		if(Objects.isNull(attendeeId)) {
			attendee = new Attendee();
		} else {
			attendee = findAttendeeById(attendeeId);
		}
		return attendee;
	}
	
	
	public Attendee findAttendeeById(Long attendeeId) {
		
		Attendee attendee = attendeeDao.findById(attendeeId).orElseThrow(() -> 
				new NoSuchElementException("Attendee with ID=" + attendeeId + " was not found"));
		
		return attendee;
	}
	
	
	
	public void copyAttendeeFields(Attendee attendee, AttendeeData attendeeData) {
		attendee.setAttendeeId(attendeeData.getAttendeeId());
		attendee.setAttendeeFirstName(attendeeData.getAttendeeFirstName());
		attendee.setAttendeeLastName(attendeeData.getAttendeeLastName());
		attendee.setAttendeeAge(attendeeData.getAttendeeAge());
		attendee.setAttendeeEmail(attendeeData.getAttendeeEmail());
	}
	

	

	@Transactional(readOnly = true)
	public List<AttendeeData> retrieveAllAttendees() {
		List<Attendee> attendees = attendeeDao.findAll();
		List<AttendeeData> result = new LinkedList<>();
		
		for (Attendee attendee : attendees) {
			AttendeeData atnData = new AttendeeData(attendee);
			
			result.add(atnData);
		}
		
		return result;
	}

	@Transactional(readOnly = true)
	public AttendeeData retrieveAttendeeById(Long attendeeId) {
		Attendee attendee = findAttendeeById(attendeeId);
		return new AttendeeData(attendee);
	}


	@Transactional(readOnly = false)
	public AttendeeData addAttendeeToEvent(Long attendeeId, Long eventId) {
		Event event = eventDao.findById(eventId).orElseThrow(() -> new NoSuchElementException("Event not found."));
		Attendee attendee = attendeeDao.findById(attendeeId).orElseThrow(() -> new NoSuchElementException("Attendee not found"));
		
		event.getAttendees().add(attendee);
		attendee.getEvents().add(event);
		
		attendeeDao.save(attendee);
		return new AttendeeData(attendee);
				
	}

	@Transactional(readOnly = false)
	public void removeAttendeeFromEvent(Long attendeeId, Long eventId) {
		Event event = eventDao.findById(eventId).orElseThrow(() -> new NoSuchElementException("Event not found"));
		Attendee attendee = attendeeDao.findById(attendeeId).orElseThrow(() -> new NoSuchElementException("Attendee not found"));
		
		event.getAttendees().remove(attendee);
		attendee.getEvents().remove(event);
		
		attendeeDao.save(attendee);
		
		
	}

	@Transactional(readOnly = false)
	public void deleteAttendeeById(Long attendeeId) {
		Attendee attendee = findAttendeeById(attendeeId);
		
		attendeeDao.delete(attendee);
		
	}
	
	
	
}
