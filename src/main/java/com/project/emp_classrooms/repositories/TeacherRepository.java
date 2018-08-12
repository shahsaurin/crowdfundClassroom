package com.project.emp_classrooms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.emp_classrooms.entities.Teacher;

public interface TeacherRepository 
	extends CrudRepository<Teacher, Integer>{
	
	@Query("SELECT t FROM Teacher t WHERE t.username=:uname AND t.password=:pwd")
	public List<Teacher> findTeacherByCredentials(@Param("uname") String uname, @Param("pwd") String pwd);

}
