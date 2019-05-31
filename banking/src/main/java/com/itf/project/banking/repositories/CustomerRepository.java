package com.itf.project.banking.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.itf.project.banking.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	List<Customer> findAll();
	
	Customer findById(long id);
	
}
