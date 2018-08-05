package com.project.emp_classrooms.repositories;

import org.springframework.data.repository.CrudRepository;

import com.project.emp_classrooms.entities.School;

public interface SchoolRepository 
	extends CrudRepository<School, Integer>{

}
