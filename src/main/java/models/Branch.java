package models;


import lombok.Data;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.beans.factory.annotation.Autowired;

@Data
@Entity
@Table(name="branch")
public class Branch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3133019351945328604L;
	
	@Id
	@Column(name = "branchname")
	private String branchname;
	
	@NotNull
	private String branchcity;
	
	@NotNull
	private Long assets;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "branch_account", joinColumns = { @JoinColumn(name = "branchname") }, inverseJoinColumns = { @JoinColumn(name = "accountid") })
	private Set<Account> accounts;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "branch_loan", joinColumns = { @JoinColumn(name = "branchname") }, inverseJoinColumns = { @JoinColumn(name = "loannumber") })
	private Set<Loan> loans;

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

	public Long getAssets() {
		return assets;
	}

	public void setAssets(Long assets) {
		this.assets = assets;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
