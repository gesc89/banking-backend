package com.itf.project.banking.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long customer_id;
	
	@NotNull
	private String firstname;
	
	@NotNull
	private String lastname;
	
	@NotNull
	private String adressStreet;
	
	@NotNull
	private String adressHouse;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	private Postalcode adress_postalcode;

	@OneToMany(mappedBy = "customer")
	private Set<Account> accounts;

	public Customer(String firstname, String lastname, String adressStreet, String adressHouse,
			Postalcode adressPostalcode) {
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.adressStreet = adressStreet;
		this.adressHouse = adressHouse;
		this.adress_postalcode = adressPostalcode;
	
		
	}
	
	public Customer() {
		
	}

	public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long id) {
		this.customer_id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAdressStreet() {
		return adressStreet;
	}

	public void setAdressStreet(String adressStreet) {
		this.adressStreet = adressStreet;
	}

	public String getAdressHouse() {
		return adressHouse;
	}

	public void setAdressHouse(String adressHouse) {
		this.adressHouse = adressHouse;
	}

	public Postalcode getAdress_postalcode() {
		return adress_postalcode;
	}

	public void setAdress_postalcode(Postalcode adressPostalcode) {
		this.adress_postalcode = adressPostalcode;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
	
}
