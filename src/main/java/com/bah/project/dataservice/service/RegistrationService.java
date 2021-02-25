package com.bah.project.dataservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bah.project.dataservice.domain.Registration;
import com.bah.project.dataservice.repository.RegistrationRepository;


@Service
public class RegistrationService{

	@Autowired
	private RegistrationRepository registrationRepository;
	
	public Iterable<Registration> getAllRegistrations() {
		return registrationRepository.findAll();
	}	
	
	public Optional<Registration> getRegistrationById(Long id) {
		return registrationRepository.findById(id);
	}

	public Registration addRegistration(Registration registration) {
		return registrationRepository.save(registration);
	}


	public Registration editRegistration(Registration registration) {
		return registrationRepository.save(registration);
	}

	public void deleteRegistration(Long id) {
		registrationRepository.deleteById(id);
	}


}
