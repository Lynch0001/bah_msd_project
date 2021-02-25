package com.bah.project.dataservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bah.project.dataservice.domain.Registration;
import com.bah.project.dataservice.service.RegistrationRepositoryImpl;

@RestController
public class RegistrationController {


	@Autowired
	private RegistrationRepositoryImpl registrationRepositoryImpl;
	
	@GetMapping("/registration/{id}")
	public Registration getRegistration(@PathVariable int id) {
		return registrationRepositoryImpl.getRegistration(id);
	}
	
	@GetMapping("/registration")
	public List<Registration> getAllRegistrations() {
		return registrationRepositoryImpl.getAllRegistrations();

	}

	@PostMapping("/registration")
	public Registration addRegistration(@RequestBody Registration registration) {
		return registrationRepositoryImpl.addRegistration(registration);
	}
	
	@PutMapping("/registration")
	public Registration editRegistration(@RequestBody Registration registration) {
		return registrationRepositoryImpl.editRegistration(registration);
	}
	
	@DeleteMapping("/registration/{id}")
	public void deleteRegistration(@PathVariable int id) {
		registrationRepositoryImpl.deleteRegistration(id);
	}
}

