package com.bah.project.dataservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bah.project.dataservice.domain.Registration;


@Repository
public class RegistrationRepositoryImpl {

	static private List<Registration> registrationsList = new ArrayList<Registration>();

	static {
		registrationsList.add(new Registration(1,  "John Smith", "Test Event 1", new Date(), "Notes"));
		registrationsList.add(new Registration(2,  "Jane Smith", "Test Event 2", new Date(), "Notes"));
	}
	
	public Registration getRegistration(int id) {
		return registrationsList.get(id-1);
	}
	
	
	public List<Registration> getAllRegistrations() {
		return registrationsList;
	}
	
	public Registration addRegistration(Registration registration) {
		Registration newRegistration = new Registration();
		newRegistration.setId(registration.getId());
		
		newRegistration.setCustomer_name(registration.getCustomer_name());
		newRegistration.setEvent_name(registration.getEvent_name());
		newRegistration.setDate(registration.getDate());
		newRegistration.setNotes(registration.getNotes());
		
		registrationsList.add(newRegistration);
		return registrationsList.get(registrationsList.size()-1);
	}		
		
	public Registration editRegistration(Registration registration) {
		
		registrationsList.get(registration.getId()-1).setCustomer_name(registration.getCustomer_name());
		registrationsList.get(registration.getId()-1).setEvent_name(registration.getEvent_name());
		registrationsList.get(registration.getId()-1).setDate(registration.getDate());
		registrationsList.get(registration.getId()-1).setNotes(registration.getNotes());		
		
		return registrationsList.get(registration.getId()-1);
	}
	
	public void deleteRegistration(int id) {
		registrationsList.remove(id-1);
	}
		
	
}
