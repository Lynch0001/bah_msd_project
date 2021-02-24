package com.bah.project.dataservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bah.project.dataservice.domain.Customer;
import com.bah.project.dataservice.service.CustomerRepositoryImpl;

@RestController
public class CustomerController {

	@Autowired
	private CustomerRepositoryImpl customerRepositoryImpl;
	
	@GetMapping("/customer/{id}")
	public Customer getCustomer(@PathVariable int id) {
		return customerRepositoryImpl.getCustomer(id);
	}
	
	@GetMapping("/customer")
	public List<Customer> getAllCustomers() {
		return customerRepositoryImpl.getAllCustomers();

	}

	@PostMapping("/customer")
	public Customer addCustomer(Customer customer) {
		return customerRepositoryImpl.addCustomer(customer);
	}
	
}
