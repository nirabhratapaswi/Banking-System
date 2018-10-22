package controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import models.Account;
import models.AccountPost;
import models.Branch;
import models.Customer;
import models.CustomerPost;
import service.AccountService;
import service.CustomerService;

@Controller
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AccountService accountService;
	
	protected CustomerController() {}
	
	@RequestMapping(value = "/list/all")
	@GetMapping
	public List<Customer> getAllCustomers() {
		// System.out.println(this.customerService.c);
		/*for (Customer c: this.customerService.getAllCustomers()) {
			System.out.println("Customer: " + c.getName() + " has the following accounts: ");
			for (Account a: c.getAccounts()) {
				System.out.print(a.getAccountnumber() + ",");
			}
			System.out.println("");
		}*/
		return this.customerService.getAllCustomers();
	}
	
	@RequestMapping(value = "/get/{customerid}")
	@GetMapping
	public @ResponseBody Customer getCustomer(@PathVariable("customerid") Long customerid) {
		Optional<Customer> customer = this.customerService.getCustomerByCustomerid(customerid);
		if (customer.isPresent()) {
			return customer.get();
		} else {
			return null;
		}
	}
	
	/*@RequestMapping(value = "/update/new", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@PostMapping
	public @ResponseBody Boolean addNewCustomer(Customer customer) {
		System.out.println("Trying to save customer: " + customer.toCustomString());
		return this.customerService.saveCustomer(customer);
	}*/
	
	@RequestMapping(value = "/update/new", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@PostMapping
	public @ResponseBody Boolean addNewCustomer(CustomerPost customerPost) {
		System.out.println("Trying to save customer: " + customerPost.toCustomString());
		Customer customer = new Customer();
		customer.setUsername(customerPost.getUsername());
		customer.setName(customerPost.getName());
		customer.setUsername(customerPost.getUsername());
		customer.setPassword(customerPost.getPassword());
		customer.setStreet(customerPost.getStreet());
		customer.setCity(customerPost.getCity());
		Set<Account> accountSet = new HashSet<>();
		Optional<Account> accountOptional = accountService.getAccount(((Integer) 6).longValue());
		if (accountOptional.isPresent()) {
			Account account = accountOptional.get();
			System.out.println("Adding account: " + account.toCustomString());
			accountSet.add(account);
			for (Account a: accountSet) {
				System.out.println("Flag 0: " + a.toCustomString());
			}
		}
		customer.setAccounts(accountSet);
		return this.customerService.saveCustomer(customer);
	}
	
	@RequestMapping(value = "/delete/{customerid}")
	@GetMapping
	public @ResponseBody Boolean deleteCustomer(@PathVariable("customerid") Long customerid) {
		return this.customerService.deleteCustomer(customerid);
	}
	
}
