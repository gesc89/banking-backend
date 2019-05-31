package com.itf.project.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itf.project.banking.models.Account;
import com.itf.project.banking.models.Customer;
import com.itf.project.banking.repositories.AccountRepository;
import com.itf.project.banking.repositories.CustomerRepository;
import com.itf.project.banking.services.CustomerUpdateServiceImpl;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private CustomerUpdateServiceImpl customerUpdateService ;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/postCustomer", consumes = "application/json")
	public ResponseEntity<?> postCustomer(@RequestBody Customer receivedCustomer) {
		
		customerRepo.save(receivedCustomer);
		
		return new ResponseEntity<>(receivedCustomer, HttpStatus.CREATED);
		
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/getCustomers")
	public ResponseEntity<?> getAllCustomers() {
		
		List<Customer> allCustomers = customerRepo.findAll();
		
		return new ResponseEntity<>(allCustomers, HttpStatus.OK);
		
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/getCustomer/{id}")
	public ResponseEntity<?> getCustomer(@PathVariable long id) {
		
		Customer receivedCustomer = customerRepo.findById(id);
		
		return new ResponseEntity<>(receivedCustomer, HttpStatus.OK);
		
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping(value = "/deleteCustomer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable long id) {
		
		Customer receivedCustomer = customerRepo.findById(id);
		
		customerRepo.delete(receivedCustomer);
		
		return new ResponseEntity<>(receivedCustomer, HttpStatus.OK);
		
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping(value = "/updateCustomer/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable long id, @RequestBody Customer dummyCustomer ) {
		
		Customer receivedCustomer = customerRepo.findById(id);
		
		customerUpdateService.updateCustomerData(receivedCustomer, dummyCustomer);
		
		customerRepo.save(receivedCustomer);
		
		return new ResponseEntity<>(receivedCustomer, HttpStatus.OK);
		
	}
	
	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping(value = "/assignAccountToCustomer/{accountId}")
	public ResponseEntity<?> assignAccountToCustomer( @PathVariable String accountId,
													 @RequestBody Customer toUpdateCustomer) {
		
		Account toAddAccount = accountRepo.findById(accountId).get();
		
		toAddAccount.setCustomer(toUpdateCustomer);
		
		toUpdateCustomer.getAccounts().add(toAddAccount);
		
		final Customer updatedCustomer = customerRepo.save(toUpdateCustomer);
		
		return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
		
	}
	
}
