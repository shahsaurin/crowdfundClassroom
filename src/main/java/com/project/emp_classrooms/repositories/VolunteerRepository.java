package com.project.emp_classrooms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.emp_classrooms.entities.Volunteer;

public interface VolunteerRepository 
	extends CrudRepository<Volunteer, Integer>{
	
	@Query("SELECT v FROM Volunteer v WHERE v.username=:uname AND v.password=:pwd")
	public List<Volunteer> findVolunteerByCredentials(@Param("uname") String uname, @Param("pwd") String pwd);

}
