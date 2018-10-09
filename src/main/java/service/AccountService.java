package service;

import org.springframework.stereotype.Service;

import models.Account;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

import repositories.AccountRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;
	
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
}
