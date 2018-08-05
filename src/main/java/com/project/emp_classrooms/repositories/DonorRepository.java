package com.project.emp_classrooms.repositories;

import org.springframework.data.repository.CrudRepository;

import com.project.emp_classrooms.entities.Donor;

public interface DonorRepository 
	extends CrudRepository<Donor, Integer>{

}
