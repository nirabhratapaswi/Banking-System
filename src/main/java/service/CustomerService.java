package service;

import org.springframework.stereotype.Service;

import models.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

import repositories.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer getCustomerByUsername(String username) {
		return customerRepository.findByUsername(username);
	}
	
	public Optional<Customer> getCustomerByCustomerid(Long customerid) {
		return customerRepository.findById(customerid);
	}
	
	public List<Customer> getAllCustomers() {
		return (List<Customer>) customerRepository.findAll();
	}
	
	public Optional<Customer> getCustomer(Long customer_id) {
		return this.customerRepository.findById(customer_id);
	}
	
	public Boolean saveCustomer(Customer customer) {
		customerRepository.save(customer);
		return true;
	}
	
	public Boolean deleteCustomer(Long customerid) {
		try {
			this.customerRepository.deleteById(customerid);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
	
}
