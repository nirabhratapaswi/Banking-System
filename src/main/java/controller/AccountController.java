package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Optional;
import java.util.Random;
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
import models.Loan;
import repositories.AccountRepository;
import repositories.BranchRepository;
import repositories.CustomerRepository;
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
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BranchRepository branchRepository;
	
	protected AccountController() {}
	
	public void testManyToMany() {
		this.accountRepository.deleteAll();
		this.customerRepository.deleteAll();;
		
		Account account = new Account();
		account.setAccountnumber(100001);
		account.setBalance(250000);
		account.setIsa("savings");
		account.setBranch(this.branchRepository.findByBranchname("NIT Trichy"));
		
		Customer customer = new Customer();
		customer.setName("Anirudh Swaminathan");
		customer.setCity("Chennai");
		customer.setStreet("Tamil Nadu");
		customer.setPassword("helloworld");
		customer.setUsername("aniswami97");
		
		account.getCustomers().add(customer);
		
		System.out.println("Saving customer and his account(for aniswami97)!!!");
		this.accountRepository.save(account);
		
		System.out.println(this.accountRepository.findByAccountnumber((long) 100001));
		customer.getAccounts().add(this.accountRepository.findAll().get(0));
		this.customerRepository.save(customer);
        
	}
	
	public Integer newAccountnumber() {
		Random rnd = new Random();
		return 100000 + rnd.nextInt(10000000);
	}
	
	@RequestMapping(value = "/list/all")
	@GetMapping
	public List<Account> getAllAccounts() {
		/*for (Account a: this.accountService.getAllAccounts()) {
			System.out.println("Account: " + a.getAccountnumber() + " belongs to the customers: ");
			for (Customer c: a.getCustomers()) {
				System.out.print(c.getName() + ",");
			}
			System.out.println("");
		}*/
//		testManyToMany();
		return this.accountService.getAllAccounts();
	}
	
	@RequestMapping(value = "/get/{accountnumber}")
	@GetMapping
	public @ResponseBody Account getAccount(@PathVariable("accountnumber") Long accountnumber) {
		Account account = this.accountService.getAccountByAccountNumber(accountnumber);
		if (account != null && account.getCustomers() != null) {
			for (Customer c: account.getCustomers()) {
				System.out.println(c.toCustomString());
			}
		} else {
			return null;
		}
		return account;
	}
	
	@RequestMapping(value = "/getwithbranchandcustomersandpayments/{accountnumber}")
	@GetMapping
	public @ResponseBody AccountPost getAccountWithBranchAndCustomers(@PathVariable("accountnumber") Long accountnumber) {
		Account account = this.accountService.getAccountByAccountNumber(accountnumber);
		AccountPost accountPost;
		if (account != null && account.getCustomers() != null) {
			accountPost = this.accountService.convertToAccountPost(account);
		} else {
			return null;
		}
		return accountPost;
	}
	
	@RequestMapping(value = "/update/new", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@PostMapping
	public @ResponseBody Boolean addNewAccount(AccountPost accountPost) {
		System.out.println("Trying to save account: " + accountPost.toCustomString());
		Account account = new Account();
		Customer customer;
		Branch branch;
		Random rnd = new Random();
		int n = 100000 + rnd.nextInt(900000);
		Integer new_account_number = this.newAccountnumber();
		while (this.accountRepository.findByAccountnumber((long) new_account_number) != null) {
			new_account_number = this.newAccountnumber();
		}
		account.setAccountnumber(new_account_number);
		account.setBalance(accountPost.getBalance());
		account.setIsa(accountPost.getIsa());
		Optional<Customer> customerOptional = customerService.getCustomer(((Integer) accountPost.getCustomerid()).longValue());
		// Optional<Customer> customerOptional = customerService.getCustomer(((Integer) 102115029).longValue());
		if (customerOptional.isPresent()) {
			customer = customerOptional.get();
			System.out.println("Adding customer: " + customer.getName());
		} else {
			return false;
		}
		Optional<Branch> branchOptional = branchService.getBranch(accountPost.getBranchname());
		if (branchOptional.isPresent()) {
			branch = branchOptional.get();
			System.out.println("Adding branch: " + branch.toCustomString());
		} else {
			return false;
		}
		account.getCustomers().add(customer);
		account.setBranch(branch);
		this.accountService.saveAccount(account);
		customer.getAccounts().add(account);
		branch.getAccounts().add(account);
		this.branchService.saveBranch(branch);
		return this.customerService.saveCustomer(customer);
	}
	
	@RequestMapping(value = "/update/existing", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@PostMapping
	public @ResponseBody Boolean updateAccount(AccountPost accountPost) {
		System.out.println("Trying to save account: " + accountPost.toCustomString());
		Account account = this.accountService.getAccountByAccountNumber(accountPost.getAccountnumber());
		if (account == null) {
			return false;
		}
		Customer customer;
		Branch branch;
		account.setBalance(accountPost.getBalance());
		account.setIsa(accountPost.getIsa());
		Optional<Customer> customerOptional = customerService.getCustomer(((Integer) accountPost.getCustomerid()).longValue());
		if (customerOptional.isPresent()) {
			customer = customerOptional.get();
			System.out.println("Adding customer: " + customer.getName());
			Boolean exists = false;
			for (Customer c: account.getCustomers()) {
				if (c.getCustomerid() == accountPost.getCustomerid()) {
					exists = true;
				}
			}
			if (!exists) {
				account.getCustomers().add(customer);
			}
		} else {
			return false;
		}
		Optional<Branch> branchOptional = branchService.getBranch(accountPost.getBranchname());
		if (branchOptional.isPresent()) {
			branch = branchOptional.get();
			System.out.println("Adding branch: " + branch.toCustomString());
		} else {
			return false;
		}
		account.setBranch(branch);
		this.accountService.saveAccount(account);
		customer.getAccounts().add(account);
		branch.getAccounts().add(account);
		this.branchService.saveBranch(branch);
		return this.customerService.saveCustomer(customer);
	}
	
	@RequestMapping(value = "/delete/{accountid}")
	@GetMapping
	public @ResponseBody Boolean deleteAccount(@PathVariable("accountid") Long accountid) {
		Optional<Account> accountOptional = this.accountService.getAccountByAccountid(accountid);
		if (!accountOptional.isPresent()) {
			return false;
		}
		return this.accountService.deleteAccount(accountid);
	}
	
}
