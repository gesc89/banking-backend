package com.itf.project.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itf.project.banking.models.Revenue;
import com.itf.project.banking.repositories.AccountRepository;
import com.itf.project.banking.repositories.RevenueRepository;
import com.itf.project.banking.services.TransferServiceImpl;

@RestController
public class RevenueController {

	@Autowired
	private RevenueRepository revenueRepo;

	@Autowired
	private TransferServiceImpl transferService;

	@Autowired
	private AccountRepository accountRepo;

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/getRevenues")
	public ResponseEntity<?> getAllRevenues() {

		List<Revenue> allRevenues = revenueRepo.findAll();

		return new ResponseEntity<>(allRevenues, HttpStatus.OK);

	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/postRevenues/{transfererAccountId}/{otherAccountId}")
	public ResponseEntity<?> postRevenue(@RequestBody List<Revenue> receivedRevenues,
			@PathVariable String transfererAccountId,
			@PathVariable String otherAccountId) {

		if (accountRepo.findById(transfererAccountId).get().getBalance() <= 0) {

			return new ResponseEntity<>("Insufficient funds", HttpStatus.FORBIDDEN);

		}
		
		if (!accountRepo.findById(otherAccountId).isPresent()) {
			
			List<Revenue> updatedRevenues = (List<Revenue>) revenueRepo.saveAll(receivedRevenues);

			transferService.transferMoneytoDifferentBank(receivedRevenues);

			return new ResponseEntity<>(updatedRevenues, HttpStatus.OK);

		}

			List<Revenue> updatedRevenues = (List<Revenue>) revenueRepo.saveAll(receivedRevenues);
	
			transferService.transferMoney(receivedRevenues);
	
			return new ResponseEntity<>(updatedRevenues, HttpStatus.OK);

	}

}
