package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
import service.AccountService;
import service.BranchService;
import service.CustomerService;

@Controller
@RestController
@RequestMapping(value = "/account")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BranchService branchService;
	
	protected AccountController() {}
	
	@RequestMapping(value = "/list/all")
	@GetMapping
	public List<Account> getAllAccounts() {
		for (Account a: this.accountService.getAllAccounts()) {
			System.out.println("Account: " + a.getAccountnumber() + " belongs to the customers: ");
			for (Customer c: a.getCustomers()) {
				System.out.print(c.getName() + ",");
			}
			System.out.println("");
		}
		return this.accountService.getAllAccounts();
	}
	
	@RequestMapping(value = "/update/new", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@PostMapping
	public @ResponseBody Boolean addNewAccount(AccountPost accountPost) {
		System.out.println("Trying to save account: " + accountPost.toCustomString());
		Account account = new Account();
		account.setBalance(accountPost.getBalance());
		account.setIsa(accountPost.getIsa());
		Set<Customer> customeridSet = new HashSet<>();
		Optional<Customer> customerOptional = customerService.getCustomer(((Integer) 102115029).longValue());
		if (customerOptional.isPresent()) {
			Customer customer = customerOptional.get();
			System.out.println("Adding customer: " + customer.getName());
			customeridSet.add(customer);
			for (Customer c: customeridSet) {
				System.out.println("Flag 0: " + c.getName());
			}
		}
		Optional<Branch> branchOptional = branchService.getBranch(accountPost.getBranchname());
		Branch branch;
		if (branchOptional.isPresent()) {
			branch = branchOptional.get();
			System.out.println("Adding branch: " + branch.toCustomString());
		} else {
			return false;
		}
		account.setCustomers(customeridSet);
		account.setBranch(branch);
		return this.accountService.saveAccount(account);
	}
}
