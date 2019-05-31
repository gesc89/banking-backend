package com.itf.project.banking.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.itf.project.banking.models.LoginCredential;

public interface LoginCredentialRepository extends CrudRepository<LoginCredential, String> {
	
	Optional<LoginCredential> findById(String id);

}
