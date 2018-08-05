package com.project.emp_classrooms.repositories;

import org.springframework.data.repository.CrudRepository;

import com.project.emp_classrooms.entities.Donation;

public interface DonationRepository 
	extends CrudRepository<Donation, Integer>{

}
