package com.bah.project.dataservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bah.project.dataservice.domain.Customer;

@Repository
public class CustomerRepositoryImpl{

	
	
	public Customer getCustomer(int id) {

		List<Customer> customers = new ArrayList<Customer>();
		customers.add(new Customer(1L, "john@mail.com", "John Smith", "password"));
		customers.add(new Customer(2L, "jane@mail.com", "Jane Smith", "password"));
		
		return customers.get(id-1);
	}
	
	
	public List<Customer> getAllCustomers() {

		List<Customer> customers = new ArrayList<Customer>();
		customers.add(new Customer(1L, "john@mail.com", "John Smith", "password"));
		customers.add(new Customer(2L, "jane@mail.com", "Jane Smith", "password"));
		
		return customers;
	}
	
	public Customer addCustomer(Customer customer) {

		Customer newCustomer = new Customer();
		newCustomer.setName(customer.getName());
		newCustomer.setEmail(customer.getEmail());
		newCustomer.setPassword(customer.getPassword());
		return newCustomer;
	}		
		
		

		
	
}
