package models;

import java.util.Set;

public class CustomerPost {
	public Long getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}

	private Long customerid;
	private String username;
	private String name;
	
	private String password;
	
	private String street;
	
	private String city;
	
	private Set<Account> accounts;
	
	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		// String hashedPassword = this.passwordEncoder.encode(password);	// encode password in service class.
		this.password = password;
	}
	
	public String toCustomString() {
		return "Username: " + this.username + ", name: " + this.name;
	}
}
