package com.bah.project.dataservice.controller;

import java.net.URI;
import java.util.Iterator;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers/{customerId}")
	public Optional<Customer> getCustomer(@PathVariable("customerId") Integer id) {
		log.debug("Customer Controller - Get by Id - id received: {}", id);
		return customerService.getCustomer(id);
	}
	
	@PostMapping("/customers/byname")
	public Customer getCustomerByName(@RequestBody String username) {
		log.debug("Customer Controller - Get by Name Method - username received: {}", username);
		Iterable<Customer> customers = customerService.getAllCustomers();
		for (Iterator<Customer> iterator = customers.iterator(); iterator.hasNext();) {
			Customer customer = (Customer) iterator.next();
			if(customer.getName() == username) {return customer;}
			
		}
		// not found
		return null;
	}
	
	@GetMapping("/customers")
	public Iterable<Customer> getAllCustomers() {
		Iterable<Customer> customers = customerService.getAllCustomers();
		log.debug("Customer Controller - Get All - customer list: {}", customers);
		return customers;

	}

	@PostMapping("/customers")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
	  if(customer.getId() != null ||                                         // for customer registration
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

	@PutMapping("/customers/{id}")
	public ResponseEntity<?> editCustomer(@RequestBody Customer customer, @PathVariable("id") Integer id) {
		log.debug("Customer Controller - Edit Customer Method - id received: {}", customer.getId());
		if(customer.getId() == 0 || 
		customer.getName()== null || 
		customer.getPassword()== null || 
		customer.getEmail() == null) {
			return ResponseEntity.badRequest().build();
		}
		
		customerService.editCustomer(customer);
		return ResponseEntity.ok().build();
		
	}
	
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {
		if(customerService.getCustomer(id) == null) {
			return ResponseEntity.badRequest().build();
		}
		customerService.deleteCustomer(id);
		return ResponseEntity.ok().build(); 
	}
}
