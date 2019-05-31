package com.itf.project.banking.services;

import com.itf.project.banking.models.Customer;

public interface CustomerUpdateServiceImpl {
	
	 void updateCustomerData(Customer receivedCustomer, Customer dummyCustomer);

}
