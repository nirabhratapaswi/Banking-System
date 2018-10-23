package controller;

import java.util.HashMap;
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
			Customer cust = customer.get();
			if (cust.getAccounts() != null) {
				for (Account a: cust.getAccounts()) {
					System.out.println(a.toCustomString());
				}
			}
			return cust;
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/getwithaccountsandloans/{customerid}")
	@GetMapping
	public @ResponseBody CustomerPost getCustomerWithAccounts(@PathVariable("customerid") Long customerid) {
		Optional<Customer> customer = this.customerService.getCustomerByCustomerid(customerid);
		Customer cust;
		if (!customer.isPresent()) {
			return null;
		}
		cust = customer.get();
		CustomerPost custPost = new CustomerPost();
		custPost.setCity(cust.getCity());
		custPost.setName(cust.getName());
		custPost.setUsername(cust.getUsername());
		custPost.setStreet(cust.getStreet());
		custPost.setAccounts(cust.getAccounts());
		custPost.setLoans(cust.getLoans());
		if (cust.getAccounts() != null) {
			for (Account a: cust.getAccounts()) {
				System.out.println(a.toCustomString());
			}
		}
		return custPost;
	}
	
	/*@RequestMapping(value = "/update/new", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@PostMapping
	public @ResponseBody Boolean addNewCustomer(Customer customer) {
		System.out.println("Trying to save customer: " + customer.toCustomString());
		return this.customerService.saveCustomer(customer);
	}*/
	
	/*@RequestMapping(value = "/update/new", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@PostMapping
	public @ResponseBody Boolean addNewCustomer(CustomerPost customerPost) {
		System.out.println("Trying to save customer: " + customerPost.toCustomString());
		Customer customer = new Customer();
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
	}*/
	
	@RequestMapping(value = "/update/new", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@PostMapping
	public @ResponseBody HashMap<String, String> addNewCustomer(CustomerPost customerPost) {
		HashMap<String, String> response = new HashMap<>();
		System.out.println("Trying to save customer: " + customerPost.toCustomString());
		Customer customer = new Customer();
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
		Boolean success = this.customerService.saveCustomer(customer);
		if (success) {
			response.put("success", "true");
			response.put("msg", "Succesfully created Customer");
		} else {
			response.put("success", "false");
			response.put("msg", "Creating Customer failed, check null values for fields.");
		}
		return response;
	}
	
	@RequestMapping(value = "/update/existing", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@PostMapping
	public @ResponseBody Boolean updateCustomer(CustomerPost customerPost) {
		System.out.println("Trying to save customer: " + customerPost.toCustomString());
		Optional<Customer> customerOptional = this.customerService.getCustomerByCustomerid(customerPost.getCustomerid());
		Customer customer;
		if (customerOptional.isPresent()) {
			customer = customerOptional.get();
		} else {
			return null;
		}
		customer.setName(customerPost.getName());
		customer.setUsername(customerPost.getUsername());
		customer.setPassword(customerPost.getPassword());
		customer.setStreet(customerPost.getStreet());
		customer.setCity(customerPost.getCity());
		return this.customerService.saveCustomer(customer);
	}
	
	@RequestMapping(value = "/delete/{customerid}")
	@GetMapping
	public @ResponseBody Boolean deleteCustomer(@PathVariable("customerid") Long customerid) {
		if (this.customerService.getCustomerByCustomerid(customerid) == null) {
			return false;
		}
		return this.customerService.deleteCustomer(customerid);
	}
	
	@RequestMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@PostMapping
	public @ResponseBody HashMap<String, String> cutomerLogin(CustomerPost customerPost) {
		HashMap<String, String> response = new HashMap<>();
		Customer customer = this.customerService.getCustomerByUsername(customerPost.getUsername());
		if (customer != null) {
			if (customer.getPassword().equals(customerPost.getPassword())) {
				response.put("success", "true");
				response.put("msg", "Successfully logged in.");
			} else {
				response.put("success", "false");
				response.put("msg", "Incorrect password, try again.");
			}
		} else {
			response.put("success", "false");
			response.put("msg", "Customer doesn't exist.");
		}
		return response;
	}
	
}
