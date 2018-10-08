package models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="Loan")
public class Loan {
	@Id
	@Column(name="loan_number")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long loan_number;
	
	@NotNull
	protected long amount;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

	public long getAmount() {
		return amount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	public String toCustomString() {
		return "Loan number: " + ((Long) this.loan_number).toString() + ", amount: " + ((Long) this.amount).toString();
	}
}
