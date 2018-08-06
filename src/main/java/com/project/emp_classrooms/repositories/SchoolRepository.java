package com.project.emp_classrooms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.emp_classrooms.entities.School;

public interface SchoolRepository 
	extends CrudRepository<School, Integer>{
	
	@Query("SELECT s FROM School s WHERE s.name=:sch_name")
	public List<School> findSchoolByName(@Param("sch_name") String name);


}
