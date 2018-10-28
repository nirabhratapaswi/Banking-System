package models;

import java.time.LocalDateTime;

public class PaymentPost {
	private long paymentnumber;
	private long paymentamount;
	// private LocalDateTime paymentdate;
	private long loannumber;
	private Loan loan;
	private Account account;
	private Long accountid;
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Long getAccountid() {
		return accountid;
	}

	public void setAccountid(Long accountid) {
		this.accountid = accountid;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	public long getPaymentnumber() {
		return paymentnumber;
	}

	public void setPaymentnumber(long paymentnumber) {
		this.paymentnumber = paymentnumber;
	}

	public long getPaymentamount() {
		return paymentamount;
	}

	public void setPaymentamount(long paymentamount) {
		this.paymentamount = paymentamount;
	}

	/*public LocalDateTime getPaymentdate() {
		return paymentdate;
	}

	public void setPaymentdate(LocalDateTime paymentdate) {
		this.paymentdate = paymentdate;
	}*/

	public long getLoannumber() {
		return loannumber;
	}

	public void setLoannumber(long loannumber) {
		this.loannumber = loannumber;
	}
	
	public String toCustomString() {
		return "Payment number: " + ((Long) this.paymentnumber).toString() + ", amount: " + ((Long) this.paymentamount).toString() + ", loannumber: " + ((Long) this.loannumber).toString() + ", accountid: " + ((Long) this.accountid);
	}
		
}
