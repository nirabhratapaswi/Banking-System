package models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.beans.factory.annotation.Autowired;

@Data
@Entity
@Table(name="customer")
public class Customer {
	@Id
	@Column(name = "customer_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customer_id;
	
	/*private BCryptPasswordEncoder passwordEncoder;
	
	protected User() {
		this.passwordEncoder = new BCryptPasswordEncoder();
	}*/
	
	@NotNull
	private String username;
	
	@NotNull
	private String name;
	
	@NotNull
	@JsonIgnore
	private String password;
	
	@NotNull
	private String street;
	
	@NotNull
	private String city;

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
		return "Customer id: " + ((Long) this.customer_id).toString() + ", username: " + this.username + ", name: " + this.name;
	}
}
