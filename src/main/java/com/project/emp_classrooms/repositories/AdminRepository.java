package com.project.emp_classrooms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.emp_classrooms.entities.Admin;
import com.project.emp_classrooms.entities.Volunteer;

public interface AdminRepository 
	extends CrudRepository<Admin, Integer>{
	
	@Query("SELECT a FROM Admin a WHERE a.username=:uname AND a.password=:pwd")
	public List<Admin> findAdminByCredentials(@Param("uname") String uname, @Param("pwd") String pwd);


}
