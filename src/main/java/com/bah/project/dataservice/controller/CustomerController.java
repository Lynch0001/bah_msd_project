package com.bah.project.dataservice.controller;

import java.net.URI;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.bah.project.dataservice.domain.Customer;
import com.bah.project.dataservice.service.CustomerService;

import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController
public class CustomerController {

	private static Logger log = LoggerFactory.getLogger(CustomerController.class);
	
    @Autowired
    private Tracer tracer;
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers/{id}")
	public Optional<Customer> getCustomer(@PathVariable("id") Integer id) {
		log.debug("Customer Controller - Get by Id - id received: {}", id);
		return customerService.getCustomer(id);
	}
	
	
	@GetMapping("/customers/byname/{username}")
	public ResponseEntity<?> getCustomerByNameFromAuthService(@PathVariable String username, UriComponentsBuilder uri) {
		log.debug("Customer Controller - Get by Name Method - username received: {}", username);
		Customer customer = customerService.getCustomerByName(username);
		return ResponseEntity.ok(customer);
	}
	
	
	
	@PostMapping("/customers/byname")
	public ResponseEntity<?> getCustomerByNameFromReact(@RequestBody String username, UriComponentsBuilder uri) {
		log.debug("Customer Controller - Get by Name Method - username received: {}", username);
		Customer customer = customerService.getCustomerByName(username);
		//return ResponseEntity.ok(customer);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(customer.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
	@GetMapping("/customers")
	public Iterable<Customer> getAllCustomers() {

		log.debug("Customer Controller - Get All Method");
		
        Span span = tracer.buildSpan("get customers").start();
		span.setTag("http.status_code", 201);
		Iterable<Customer> customers = customerService.getAllCustomers();
		span.finish();
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
