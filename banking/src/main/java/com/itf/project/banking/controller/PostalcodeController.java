package com.itf.project.banking.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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

import com.itf.project.banking.models.Postalcode;
import com.itf.project.banking.repositories.PostalcodeRepository;
import com.itf.project.banking.services.PostalcodeImportService;

@RestController
public class PostalcodeController {
	
	@Autowired
	private PostalcodeRepository postRepo;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/postPostalcode", consumes = "application/json")
	public ResponseEntity<?> postPostalcode(@RequestBody Postalcode receivedPostalcode) {
		
		postRepo.save(receivedPostalcode);
		
		return new ResponseEntity<>(receivedPostalcode, HttpStatus.CREATED);
		
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/getPostalcodes")
	public ResponseEntity<?> getAllPostalcodes() {
		
		List<Postalcode> allPostalcodes = postRepo.findAll();
		
		return new ResponseEntity<>(allPostalcodes, HttpStatus.OK);
	
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/getPostalcode/{id}")
	public ResponseEntity<?> getPostalcode(@PathVariable String id) {
		
		Postalcode receivedPostalcode = postRepo.findById(id).get();
		
		return new ResponseEntity<>(receivedPostalcode, HttpStatus.OK);
	
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/postPostalcodes", consumes = "application/json")
	public ResponseEntity<?> postPostalcodes() throws FileNotFoundException, IOException {
		
		ArrayList<String> p;
		
		p = PostalcodeImportService.importPostalcodesAsList();
		
		List<Postalcode> receivedPostalcodes = PostalcodeImportService.postalcodeFactory(p);
		
		postRepo.saveAll(receivedPostalcodes);
		
		return new ResponseEntity<>(receivedPostalcodes, HttpStatus.CREATED);
		
	}
	
}
