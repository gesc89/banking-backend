package com.itf.project.banking.services;

import org.springframework.stereotype.Service;

@Service
public class HashService implements HashServiceImpl {
	
	@Override
	public String hashPassword(String password) {
		
		return Integer.toString(password.hashCode());
		
	}

}
