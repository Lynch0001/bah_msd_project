package com.bah.project.dataservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bah.project.dataservice.domain.Customer;

@Repository
public class CustomerRepositoryImpl{

	static private List<Customer> customersList = new ArrayList<Customer>();

	static {
		customersList.add(new Customer(1, "john@mail.com", "John Smith", "password"));
		customersList.add(new Customer(2, "jane@mail.com", "Jane Smith", "password"));
	}
	
	public Customer getCustomer(int id) {
		return customersList.get(id-1);
	}
	
	
	public List<Customer> getAllCustomers() {
		return customersList;
	}
	
	public Customer addCustomer(Customer customer) {
		Customer newCustomer = new Customer();
		newCustomer.setId(customer.getId());
		newCustomer.setName(customer.getName());
		newCustomer.setEmail(customer.getEmail());
		newCustomer.setPassword(customer.getPassword());
		customersList.add(newCustomer);
		return customersList.get(customersList.size()-1);
	}		
		
	public Customer editCustomer(Customer customer) {
		
		customersList.get(customer.getId()-1).setName(customer.getName());
		customersList.get(customer.getId()-1).setPassword(customer.getPassword());
		customersList.get(customer.getId()-1).setEmail(customer.getEmail());
		
		return customersList.get(customer.getId()-1);
	}
	
	public void deleteCustomer(int id) {
		customersList.remove(id-1);
	}
		
	
}
