package com.project.emp_classrooms.repositories;

import org.springframework.data.repository.CrudRepository;

import com.project.emp_classrooms.entities.Volunteer;

public interface VolunteerRepository 
	extends CrudRepository<Volunteer, Integer>{

}
