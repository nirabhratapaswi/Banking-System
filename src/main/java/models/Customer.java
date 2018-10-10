package models;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.beans.factory.annotation.Autowired;

@Data
@Entity
@Table(name="customer")
@JsonIgnoreProperties(
		value = {"password"},
		allowGetters = true
)
public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6758563944993544313L;

	@Id
	@Column(name = "customerid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerid;
	
	@NotNull
	@Column(name = "username")
	private String username;
	
	@NotNull
	@Column(name = "name")
	private String name;
	
	@NotNull
	@JsonIgnore
	@Column(name = "password")
	private String password;
	
	@NotNull
	@Column(name = "street")
	private String street;
	
	@NotNull
	@Column(name = "city")
	private String city;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="customer_account", joinColumns = {@JoinColumn(name = "customerid", referencedColumnName = "customerid")}, inverseJoinColumns = {@JoinColumn(name = "accountnumber", referencedColumnName = "accountnumber")})
	private Set<Account> accounts;
	
	/*private BCryptPasswordEncoder passwordEncoder;
	
	protected User() {
		this.passwordEncoder = new BCryptPasswordEncoder();
	}*/

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getCustomerid() {
		return customerid;
	}

	public void setCustomerid(long customerid) {
		this.customerid = customerid;
	}
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		// String hashedPassword = this.passwordEncoder.encode(password);	// encode password in service class.
		this.password = password;
	}
	
	public String toCustomString() {
		return "Customer id: " + ((Long) this.customerid).toString() + ", username: " + this.username + ", name: " + this.name;
	}
}
