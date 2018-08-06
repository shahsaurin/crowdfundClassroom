package com.project.emp_classrooms.repositories;

import org.springframework.data.repository.CrudRepository;

import com.project.emp_classrooms.entities.Message;

public interface MessageRepository 
	extends CrudRepository<Message, Integer>{
	
}
