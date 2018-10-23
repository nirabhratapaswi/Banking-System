package models;

public class LoanPost {
	private long loannumber;
	protected long amount;
	private Long customerid;
	private String branchname;

	public long getLoannumber() {
		return loannumber;
	}

	public void setLoannumber(long loannumber) {
		this.loannumber = loannumber;
	}

	public String getBranchname() {
		return branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}

	public long getAmount() {
		return amount;
	}

	public Long getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	public String toCustomString() {
		return "Loan number: " + ((Long) this.loannumber).toString() + ", amount: " + ((Long) this.amount).toString() + ", branchname: " + this.branchname + ", customerid: " + this.customerid.toString();
	}
}
