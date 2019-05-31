package com.itf.project.banking.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class LoginCredential {
	
	@Id
	private String id;
	
	private String password;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	private Customer customer;
	
	public LoginCredential(String id, String password, Customer customer) {


		this.id = id;
		this.password = password;
		this.customer = customer;
		
	}

	public LoginCredential() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
