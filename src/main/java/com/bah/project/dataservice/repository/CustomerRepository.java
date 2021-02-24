package com.bah.project.dataservice.repository;



import java.util.List;

import com.bah.project.dataservice.domain.Customer;

public interface CustomerRepository {

	public Customer getCustomer(Long id);
	public List<Customer> getAllCustomers();
	public Customer addCustomer(Customer customer);
	public Customer editCustomer(Customer customer);
	public void deleteCustomer(Long id);
	
}
