package com.project.emp_classrooms.repositories;

import org.springframework.data.repository.CrudRepository;

import com.project.emp_classrooms.entities.Teacher;

public interface TeacherRepository 
	extends CrudRepository<Teacher, Integer>{

}
