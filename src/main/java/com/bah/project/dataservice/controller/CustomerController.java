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

import com.bah.project.dataservice.domain.Customer;
import com.bah.project.dataservice.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customer/{id}")
	public Optional<Customer> getCustomer(@PathVariable Integer id) {
		return customerService.getCustomer(id);
	}
	
	@GetMapping("/customer")
	public Iterable<Customer> getAllCustomers() {
		return customerService.getAllCustomers();

	}

	@PostMapping("/customer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
	  if(customer.getId() != 0 || 
			  customer.getName() == null ||
			  customer.getPassword()== null ||
			  customer.getEmail() == null) 
	  { 
		  return ResponseEntity.badRequest().build(); 
		  }
		  
		  Customer newCustomer = customerService.addCustomer(customer); 
		  
		  URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(newCustomer.getId()).toUri();
			ResponseEntity<?> response = ResponseEntity.created(location).build();
		  
		  return response;
	}
	
	@PutMapping("/customer")
	public ResponseEntity<?> editCustomer(@RequestBody Customer customer, @PathVariable Integer id) {

		if(customer.getId() != id || 
		customer.getName()== null || 
		customer.getEmail() == null) {
			return ResponseEntity.badRequest().build();
		}
		
		customerService.editCustomer(customer);
		return ResponseEntity.ok().build();
		
	}
	
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {
		if(customerService.getCustomer(id) == null) {
			return ResponseEntity.badRequest().build();
		}
		customerService.deleteCustomer(id);
		return ResponseEntity.ok().build(); 
	}
}
