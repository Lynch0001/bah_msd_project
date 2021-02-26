package com.bah.project.dataservice.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bah.project.dataservice.domain.Customer;
import com.bah.project.dataservice.repository.CustomerRepository;


@Service
public class CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	public Optional<Customer> getCustomer(Integer id) {
		return customerRepository.findById(id);
	}
	
	public Iterable<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}		
		
	public Customer editCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public void deleteCustomer(Integer id) {
		customerRepository.deleteById(id);
	}
		
	
}
