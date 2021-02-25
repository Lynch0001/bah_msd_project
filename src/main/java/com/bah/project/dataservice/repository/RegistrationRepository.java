package com.bah.project.dataservice.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bah.project.dataservice.domain.Registration;

@Repository
public interface RegistrationRepository extends CrudRepository<Registration, Long>{

	
}
