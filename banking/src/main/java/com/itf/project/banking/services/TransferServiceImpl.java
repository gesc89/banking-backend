package com.itf.project.banking.services;

import java.util.List;

import com.itf.project.banking.models.Revenue;

public interface TransferServiceImpl {
	
	void transferMoney(List<Revenue> revenues);
	
	void transferMoneytoDifferentBank(List<Revenue> revenues);
	
}
