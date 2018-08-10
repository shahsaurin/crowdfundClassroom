package com.project.emp_classrooms.repositories;

import org.springframework.data.repository.CrudRepository;

import com.project.emp_classrooms.entities.Person;

public interface PersonRepository 
	extends CrudRepository<Person, Integer>{

}
