package com.itf.project.banking.services;

import org.springframework.stereotype.Service;

import com.itf.project.banking.models.Customer;

@Service
public class CustomerUpdateService implements CustomerUpdateServiceImpl {

	@Override
	public void updateCustomerData(Customer receivedCustomer, Customer dummyCustomer) {

		if (!dummyCustomer.getFirstname().isEmpty() || dummyCustomer.getFirstname() == null) {

			receivedCustomer.setFirstname(dummyCustomer.getFirstname());

		}

		if (!dummyCustomer.getLastname().isEmpty() || dummyCustomer.getLastname() == null) {

			receivedCustomer.setLastname(dummyCustomer.getLastname());

		}

		if (!dummyCustomer.getAdressStreet().isEmpty() || dummyCustomer.getAdressStreet() == null) {

			receivedCustomer.setAdressStreet(dummyCustomer.getAdressStreet());

		}

		if (!dummyCustomer.getAdressHouse().isEmpty() || dummyCustomer.getAdressHouse() == null) {

			receivedCustomer.setAdressHouse(dummyCustomer.getAdressHouse());

		}

		if (!dummyCustomer.getAdress_postalcode().getZipcode().isEmpty() || dummyCustomer.getAdress_postalcode().getZipcode() == null) {

			receivedCustomer.setAdress_postalcode(dummyCustomer.getAdress_postalcode());
		}


	}

}
