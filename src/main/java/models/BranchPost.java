package models;

import java.util.Set;

public class BranchPost {
	private String branchname;
	private String branchcity;
	private Long assets;
	private Set<Account> accounts;
	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Set<Loan> getLoans() {
		return loans;
	}

	public void setLoans(Set<Loan> loans) {
		this.loans = loans;
	}

	private Set<Loan> loans;

	public Long getAssets() {
		return assets;
	}

	public void setAssets(Long assets) {
		this.assets = assets;
	}
	
	public String toCustomString() {
		return "Branch name: " + this.branchname + ", city: " + this.branchcity + ", assets: " + ((Long) this.assets).toString();
	}

	public String getBranchname() {
		return branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}

	public String getBranchcity() {
		return branchcity;
	}

	public void setBranchcity(String branchcity) {
		this.branchcity = branchcity;
	}
}
