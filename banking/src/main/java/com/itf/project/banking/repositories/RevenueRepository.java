package com.itf.project.banking.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.itf.project.banking.models.Revenue;

public interface RevenueRepository extends CrudRepository <Revenue, Long> {

	List<Revenue> findAll();
	
	Revenue findById(long id);
	
	//List<Revenue> saveAll(List<Revenue> revenues);
	
}
