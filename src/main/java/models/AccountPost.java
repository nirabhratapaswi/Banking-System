package models;

import java.util.List;

public class AccountPost {
	private long balance;
	private String isa;
	private String branchname;
	public Integer customerid;
	
	public String getIsa() {
		return isa;
	}

	public void setIsa(String isa) {
		this.isa = isa;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}
		
	public String getBranchname() {
		return branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}

	public Integer getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}

	public String toCustomString() {
		return "Balance: " + ((Long) this.balance).toString() + ", isa: " + this.isa + ", branchname: " + this.branchname + ", customerid: " + this.customerid;
	}
	
}
