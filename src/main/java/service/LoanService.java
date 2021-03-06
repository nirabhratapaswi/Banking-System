package service;

import org.springframework.stereotype.Service;

import models.Loan;
import models.LoanPost;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

import repositories.LoanRepository;

@Service
public class LoanService {
	@Autowired
	private LoanRepository loanRepository;
	
	protected LoanService() {
		System.out.println("Loan Service initialized.");
	}
	
	public List<Loan> getAllLoans() {
		return (List<Loan>) loanRepository.findAll();
	}
	
	public Optional<Loan> getLoan(Long loannumber) {
		return loanRepository.findById(loannumber);
	}
	
	public Boolean saveLoan(Loan loan) {
		try {
			loanRepository.save(loan);
		}
		catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		return true;
	}
	
	public Boolean deleteLoan(long loannumber) {
		try {
			this.loanRepository.deleteById(loannumber);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	public LoanPost convertToLoanPost(Loan loan) {
		LoanPost loanPost = new LoanPost();
		loanPost.setAmount(loan.getAmount());
		loanPost.setBranchname(loan.getBranch().getBranchname());
		loanPost.setCustomerid(loan.getCustomer().getCustomerid());
		loanPost.setLoannumber(loan.getLoannumber());
		loanPost.setBranch(loan.getBranch());
		loanPost.setPayments(loan.getPayments());
		loanPost.setCustomer(loan.getCustomer());
		return loanPost;
	}
}
