package com.bah.project.dataservice.repository;

import java.util.List;

import com.bah.project.dataservice.domain.Registration;

public interface RegistrationRepository {

	public Registration getRegistration(Long id);
	public List<Registration> getAllRegistrations();
	public Registration addRegistration(Registration registration);
	public Registration editRegistration(Registration registration);
	public void deleteRegistration(Long id);
	
}
