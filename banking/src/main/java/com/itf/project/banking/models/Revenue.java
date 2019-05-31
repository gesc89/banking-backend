package com.itf.project.banking.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Revenue {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	@Digits(integer=6, fraction=2)
	private double value;

	private Date date;

	@NotNull
	private String usage;

	@NotNull
	private String transfererAccountId;
	
	@NotNull
	private String receiverAccountId;
	
	@NotNull
	@Pattern(regexp = "[H,S]")
	private String accountingStatus;

	public Revenue(double value, String usage, String transfererAccountId, String receiverAccountId, String accountingStatus) {

		this.value = value;
		this.date = new Date();
		this.usage = usage;
		this.transfererAccountId = transfererAccountId;
		this.receiverAccountId = receiverAccountId;
		this.accountingStatus = accountingStatus;

	}

	public Revenue() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getTransfererAccountId() {
		return transfererAccountId;
	}

	public void setTransfererAccountId(String transfererAccountId) {
		this.transfererAccountId = transfererAccountId;
	}

	public String getReceiverAccountId() {
		return receiverAccountId;
	}
	
	public void setReceiverAccountId(String receiverAccountId) {
		this.receiverAccountId = receiverAccountId;
	}

	public String getAccountingStatus() {
		return accountingStatus;
	}

	public void setAccountingStatus(String accountingStatus) {
		this.accountingStatus = accountingStatus;
	}

}
