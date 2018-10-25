package service;

import org.springframework.stereotype.Service;

import models.Account;
import models.AccountPost;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

import repositories.AccountRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;
	
	public Optional<Account> getAccountByAccountid(Long accountid) {
		return accountRepository.findById(accountid);
	}
	
	public Account getAccountByAccountNumber(Long account_number) {
		return accountRepository.findByAccountnumber(account_number);
	}
	
	public List<Account> getAllAccounts() {
		return (List<Account>) accountRepository.findAll();
	}
	
	public Optional<Account> getAccount(Long account_number) {
		return this.accountRepository.findById(account_number);
	}
	
	public Boolean saveAccount(Account account) {
		accountRepository.save(account);
		return true;
	}
	
	public Boolean deleteAccount(long accountnumber) {
		try {
			this.accountRepository.deleteById(accountnumber);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	public AccountPost convertToAccountPost(Account account) {
		AccountPost accountPost = new AccountPost();
		accountPost.setAccountnumber(account.getAccountnumber());
		accountPost.setBalance(account.getBalance());
		accountPost.setBranch(account.getBranch());
		accountPost.setCustomers(account.getCustomers());
		accountPost.setBranchname(account.getBranch().getBranchname());
		accountPost.setIsa(account.getIsa());
		return accountPost;
	}
}
