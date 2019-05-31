package com.itf.project.banking.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itf.project.banking.models.Account;
import com.itf.project.banking.repositories.AccountRepository;

@RestController
public class AccountController {
	
	@Autowired
	private AccountRepository accountRepo;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/postAccount", consumes = "application/json")
	public ResponseEntity<?> postAccount(@RequestBody @Valid Account receivedAccount) {
		
		receivedAccount.setBalance(5000);
		
		accountRepo.save(receivedAccount);
		
		return new ResponseEntity<>(receivedAccount, HttpStatus.CREATED);
		
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/getAccounts")
	public ResponseEntity<?> getAllAccounts() {
		
		List<Account> allAccounts = accountRepo.findAll();
		
		return new ResponseEntity<>(allAccounts, HttpStatus.OK);
	
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/getAccount/{id}")
	public ResponseEntity<?> getAccount(@PathVariable String id) {
		
		Optional<Account> receivedAccountOptional = accountRepo.findById(id);
		
		if(receivedAccountOptional.isPresent()) {
			
			Account receivedAccount = receivedAccountOptional.get();
			
			return new ResponseEntity<>(receivedAccount, HttpStatus.OK);
			
		}
		
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping(value = "/deleteAccount/{id}")
	public ResponseEntity<Void> deleteAccount(@PathVariable String id) {
		
		return accountRepo.findById(id).map(account -> {
			
			accountRepo.delete(account);
			
			return new ResponseEntity<Void>(HttpStatus.OK);
			
		}).orElse(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
		
	}
	
}
