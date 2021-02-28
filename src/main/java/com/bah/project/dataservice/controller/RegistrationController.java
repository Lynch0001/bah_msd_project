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

import com.bah.project.dataservice.domain.Registration;
import com.bah.project.dataservice.service.RegistrationService;

@RestController
public class RegistrationController {


	@Autowired
	private RegistrationService registrationService;
	
	@GetMapping("/registration/{id}")
	public Optional<Registration> getRegistration(@PathVariable Integer id) {
		return registrationService.getRegistrationById(id);
	}
	
	@GetMapping("/registrations")
	public Iterable<Registration> getAllRegistrations() {
		return registrationService.getAllRegistrations();

	}

	
	  @PostMapping("/registrations") 
	  public ResponseEntity<?> addRegistration(@RequestBody Registration registration, ServletUriComponentsBuilder uri) {
	  if(registration.getId() != 0 || registration.getCustomer_id() == null ||
	  registration.getEvent_id() == null || registration.getRegistration_date() == null ||
	  registration.getNotes() == null) { return
	  ResponseEntity.badRequest().build(); }
	  
	  Registration newRegistration = registrationService.addRegistration(registration); 
	  
	  URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newRegistration.getId()).toUri();
		ResponseEntity<?> response = ResponseEntity.created(location).build();
	  
	  return response;
	  }
	
	
	  @PutMapping("/registrations/{id}") 
	  public ResponseEntity<?> editRegistration(@RequestBody Registration registration, @PathVariable Integer id) { 

		if(registration.getId() == 0 || 
		registration.getEvent_id() == null || 
		registration.getCustomer_id() == null ||
		registration.getRegistration_date() == null ||
		registration.getNotes() == null) {
			return ResponseEntity.badRequest().build();
		}
		
		registrationService.editRegistration(registration);
		return ResponseEntity.ok().build();  
	  }	  
	 
	
	@DeleteMapping("/registrations/{id}")
	public ResponseEntity<?> deleteRegistration(@PathVariable Integer id) {
		if(registrationService.getRegistrationById(id) == null) {
			return ResponseEntity.badRequest().build();
		}
		registrationService.deleteRegistration(id);
		return ResponseEntity.ok().build(); 
	}
}

