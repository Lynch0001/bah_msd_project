package com.bah.project.dataservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bah.project.dataservice.domain.Event;
import com.bah.project.dataservice.repository.EventRepository;

@Service
public class EventService{
	@Autowired
	private EventRepository eventRepository;
	
public Optional<Event> getEventOptional(Long id) {
	return eventRepository.findById(id);
	}
	
 public Iterable<Event> getAllEvents(){
	 return eventRepository.findAll();
		 
	 }
	 
 public Event addEvent(Event event) {
		 return eventRepository.save(event);
	 }
public Event editEvent(Event event) {
	 return eventRepository.save(event);
		 
	 }
public void deleteEvent(Long id) {
	eventRepository.deleteById(id);
	 
}

}
