package com.itf.project.banking.controller;

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

import com.itf.project.banking.models.LoginCredential;
import com.itf.project.banking.repositories.LoginCredentialRepository;
import com.itf.project.banking.services.HashServiceImpl;

@RestController
public class LoginController {
	
	@Autowired
	private LoginCredentialRepository loginRepo;
	
	@Autowired
	private HashServiceImpl hashService;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/postLoginCredential", consumes = "application/json")
	public ResponseEntity<?> postLoginCredential(@RequestBody LoginCredential loginCredential) {
		
		String hashedPassword = hashService.hashPassword(loginCredential.getPassword());
		
		loginCredential.setPassword(hashedPassword);
		
		loginRepo.save(loginCredential);
		
		return new ResponseEntity<>(loginCredential, HttpStatus.CREATED);
		
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/getLoginCredential/{id}")
	public ResponseEntity<?> getLoginCredential(@PathVariable String id) {
		
		if(loginRepo.findById(id).isPresent()) {
		
			LoginCredential loginCredential = loginRepo.findById(id).get();
			
			return new ResponseEntity<>(loginCredential, HttpStatus.OK);
			
		} else {
			
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
			
		}
	
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping(value = "/deleteLoginCredential/{id}")
	public ResponseEntity<?> deletetLoginCredential(@PathVariable String id) {
		
		if(loginRepo.findById(id).isPresent()) {
		
			LoginCredential loginCredential = loginRepo.findById(id).get();
			
			loginRepo.delete(loginCredential);
			
			return new ResponseEntity<>(loginCredential, HttpStatus.OK);
		
		} else {
			
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
			
		}
	
	}
	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/checkPassword/{id}/{password}")
	public boolean checkPassword(@PathVariable String id, @PathVariable String password) {
		
		LoginCredential loginCredential = loginRepo.findById(id).get();
		
		String hashedPassword = hashService.hashPassword(password);
		
		if(loginCredential.getPassword().equals(hashedPassword)) {
			
			return true;
			
		} else {
			
			return false;
			
		}
	
	}
	
}
