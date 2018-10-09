package models;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	/*private BCryptPasswordEncoder passwordEncoder;
	
	protected User() {
		this.passwordEncoder = new BCryptPasswordEncoder();
	}*/

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
