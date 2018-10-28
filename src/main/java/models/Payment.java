package models;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.beans.factory.annotation.Autowired;

@Data
@Entity
@Table(name="payment")
public class Payment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8597639923197661412L;
	
	@Id
	@Column(name = "paymentnumber")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long paymentnumber;
	
	@NotNull
	private long paymentamount;
	
	@NotNull
	private LocalDateTime paymentdate;
	
	// @JsonIgnore
	@ManyToOne
	@JoinColumn(name="loannumber")
	private Loan loan;
	
	@ManyToOne
	@JoinColumn(name="accountid")
	private Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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

	public LocalDateTime getPaymentdate() {
		return paymentdate;
	}

	public void setPaymentdate(LocalDateTime paymentdate) {
		this.paymentdate = paymentdate;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String toCustomString() {
		return "Payment number: " + ((Long) this.paymentnumber).toString() + ", amount: " + ((Long) this.paymentamount).toString();
	}
		
}
