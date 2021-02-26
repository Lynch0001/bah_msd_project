package com.bah.project.dataservice.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bah.project.dataservice.domain.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{

	
}
