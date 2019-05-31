package com.itf.project.banking.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.itf.project.banking.models.Postalcode;

public interface PostalcodeRepository extends CrudRepository<Postalcode, String> {

	List<Postalcode> findAll();
	
	Optional<Postalcode> findById(String id);
	
}
