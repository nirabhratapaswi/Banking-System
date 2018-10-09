package models;

import lombok.Data;

import java.io.Serializable;
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
public class Loan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2307852772530972175L;

	@Id
	@Column(name="loannumber")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long loannumber;
	
	@NotNull
	@Column(name="amount")
	protected long amount;
	
	@ManyToOne
	@JoinColumn(name="customerid")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="branchname")
	private Branch branch;

	public long getLoannumber() {
		return loannumber;
	}

	public void setLoannumber(long loannumber) {
		this.loannumber = loannumber;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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
		return "Loan number: " + ((Long) this.loannumber).toString() + ", amount: " + ((Long) this.amount).toString();
	}
}
