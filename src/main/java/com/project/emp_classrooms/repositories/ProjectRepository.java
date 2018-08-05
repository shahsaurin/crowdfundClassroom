package com.project.emp_classrooms.repositories;

import org.springframework.data.repository.CrudRepository;

import com.project.emp_classrooms.entities.Project;

public interface ProjectRepository 
	extends CrudRepository<Project, Integer>{

}
