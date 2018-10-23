package controller;

import java.util.List;

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
import java.util.Optional;

import models.Branch;
import models.Customer;
import models.Loan;
import models.LoanPost;
import service.LoanService;
import service.CustomerService;
import service.BranchService;

@Controller
@RestController
@RequestMapping(value = "/loan")
public class LoanController {
	@Autowired
	private LoanService loanService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private BranchService branchService;
	
	protected LoanController() {}
	
	@RequestMapping(value = "/list/all")
	@GetMapping
	public List<Loan> getAllLoans() {
		return this.loanService.getAllLoans();
	}
	
	@RequestMapping(value = "/get/{loannumber}")
	@GetMapping
	public @ResponseBody Loan getLoan(@PathVariable("loannumber") Long loannumber) {
		Optional<Loan> loan = this.loanService.getLoan(loannumber);
		if (loan.isPresent()) {
			return loan.get();
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/update/new", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@PostMapping
	public @ResponseBody Boolean addNewLoan(LoanPost loanPost) {
		Loan loan = new Loan();
		Customer customer;
		loan.setAmount(loanPost.getAmount());
		Branch branch = this.branchService.getBranchByBranchName(loanPost.getBranchname());
		if (branch == null) {
			return false;
		}
		loan.setBranch(branch);
		Optional<Customer> customerOptional = this.customerService.getCustomer(loanPost.getCustomerid());
		if (!customerOptional.isPresent()) {
			return false;
		}
		customer = customerOptional.get();
		loan.setCustomer(customer);
		this.loanService.saveLoan(loan);
		customer.getLoans().add(loan);
		this.customerService.saveCustomer(customer);
		branch.getLoans().add(loan);
		return this.branchService.saveBranch(branch);
	}
	
	@RequestMapping(value = "/delete/{loannumber}")
	@GetMapping
	public @ResponseBody Boolean deleteLoan(@PathVariable("loannumber") Long loannumber) {
		Optional<Loan> loanOptional = this.loanService.getLoan(loannumber);
		if (!loanOptional.isPresent()) {
			return false;
		}
		return this.loanService.deleteLoan(loannumber);
	}
	
}
