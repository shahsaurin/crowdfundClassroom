package com.project.emp_classrooms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.emp_classrooms.entities.Donor;
import com.project.emp_classrooms.entities.Teacher;

public interface DonorRepository 
	extends CrudRepository<Donor, Integer>{

	@Query("SELECT d FROM Donor d WHERE d.username=:uname AND d.password=:pwd")
	public List<Donor> findDonorByCredentials(@Param("uname") String uname, @Param("pwd") String pwd);

}
