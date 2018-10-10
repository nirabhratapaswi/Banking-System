package models;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.beans.factory.annotation.Autowired;

@Data
@Entity
@Table(name="account")
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6204005124799397466L;
	
	@Id
	@Column(name = "accountnumber")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accountnumber;
	
	@NotNull
	@Column(name = "balance")
	private long balance;
	
	@NotNull
	@Column(name = "isa")
	private String isa;
	
	public String getIsa() {
		return isa;
	}

	public void setIsa(String isa) {
		this.isa = isa;
	}

	@ManyToOne
	@JoinColumn(name="branchname")
	private Branch branch;
	
	/*@ManyToOne
	@JoinColumn(name="customerid")
	private Customer customer;*/
	
	@JsonIgnore
	@ManyToMany(mappedBy = "accounts")
	public Set<Customer> customers;

	public long getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(long accountnumber) {
		this.accountnumber = accountnumber;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	/*public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}*/
	
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	public String toCustomString() {
		return "Account number: " + ((Long) this.accountnumber).toString() + ", balance: " + ((Long) this.balance).toString() + ", isa: " + this.isa;
	}
	
}
