package controller;

import java.util.List;

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
import java.util.Optional;

import models.Customer;
import models.Loan;
import service.LoanService;
import service.CustomerService;

@Controller
@RestController
@RequestMapping(value = "/loan")
public class LoanController {
	@Autowired
	private LoanService loanService;
	@Autowired
	private CustomerService customerService;
	
	protected LoanController() {}
	
	@RequestMapping(value = "/list/all")
	@GetMapping
	public List<Loan> getAllLoans() {
		return this.loanService.getAllLoans();
	}
	
	@RequestMapping(value = "/update/new", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@PostMapping
	public @ResponseBody Boolean addNewLoan(Loan loan) {
		/*System.out.println("Customer id: " + loanExtended.getCustomerId());
		System.out.println("Trying to save loan: " + loanExtended.toCustomString());
		Optional<Customer> customer = customerService.getCustomer(loanExtended.getCustomerId());
		if (customer.isPresent()) {
			Customer loan_customer = customer.get();
			Loan new_loan = new Loan();
			new_loan.setAmount(loanExtended.getAmount());
			new_loan.setCustomer(loan_customer);
			System.out.println("New Loan: " + new_loan.toCustomString());
			return this.loanService.saveLoan(new_loan);
		} else {
			return false;
		}*/
		System.out.println("Trying to save loan: " + loan.toCustomString());
		return this.loanService.saveLoan(loan);
	}
}
