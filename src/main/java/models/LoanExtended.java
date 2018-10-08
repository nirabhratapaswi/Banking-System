package models;

public class LoanExtended extends Loan {
	private long customer_id;
	
	protected LoanExtended() {
		this.customer_id = 1;
	}
	
	public long getCustomerId() {
		return this.customer_id;
	}
	
	public void setCustomerId(long customer_id) {
		this.customer_id = customer_id;
	}
	
	@Override
	public String toCustomString() {
		return "Customer id: " + ((Long) this.customer_id).toString() + ", amount: " + ((Long) this.amount).toString();
	}
}
