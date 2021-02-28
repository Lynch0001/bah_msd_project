package com.bah.project.dataservice.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bah.project.dataservice.domain.Event;
import com.bah.project.dataservice.service.EventService;

@RestController
public class EventController {

	@Autowired
	private EventService eventService;
	
	@GetMapping("/events/{id}")
	public Optional<Event> getEvent(@PathVariable Integer id) {
		return eventService.getEventOptional(id);
	}
	
	@GetMapping("/events")
	public Iterable<Event> getAllEvents() {
		return eventService.getAllEvents();
	}
	
	@PostMapping("/events")
	public ResponseEntity<?> addEvent(@RequestBody Event event, ServletUriComponentsBuilder uri) {
		if (event.getId()!=0 || 
				event.getTitle()== null ||
				event.getCode() == null ||
				event.getDescription() == null)
					{return ResponseEntity.badRequest().build();}
			
		Event newEvent = eventService.addEvent(event);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newEvent.getId()).toUri();
		ResponseEntity<?> response = ResponseEntity.created(location).build();
		
		return response;
}
	
	
	@PutMapping("/events/{id}")
	public ResponseEntity<?> editEvent(@RequestBody Event event, @PathVariable Integer id ){

	if(event.getId()== 0||
		event.getTitle() == null ||
		event.getCode()== null ||
		event.getDescription()== null )
	{return ResponseEntity.badRequest().build();}
	
	eventService.editEvent(event);
	return ResponseEntity.ok().build();
}
	
@DeleteMapping("/events/{id}")
public ResponseEntity<?> deleteEvent(@PathVariable Integer id){
	if(eventService.getEventOptional(id) == null) {
	 return ResponseEntity.badRequest().build();
	}
	eventService.deleteEvent(id);
	return ResponseEntity.ok().build();
}
}