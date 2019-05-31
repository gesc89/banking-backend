package com.itf.project.banking.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.itf.project.banking.models.Account;

public interface AccountRepository extends CrudRepository <Account, String> {

	List<Account> findAll();
	
	Optional<Account> findById(String id);
	
}
