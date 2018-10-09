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

import models.Account;
import service.AccountService;

@Controller
@RestController
@RequestMapping(value = "/account")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	protected AccountController() {}
	
	@RequestMapping(value = "/list/all")
	@GetMapping
	public List<Account> getAllAccounts() {
		return this.accountService.getAllAccounts();
	}
	
	@RequestMapping(value = "/update/new", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@PostMapping
	public @ResponseBody Boolean addNewAccount(Account account) {
		System.out.println("Trying to save account: " + account.toCustomString());
		return this.accountService.saveAccount(account);
	}
}
