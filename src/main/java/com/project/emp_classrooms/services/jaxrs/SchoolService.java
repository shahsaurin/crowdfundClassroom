package com.project.emp_classrooms.services.jaxrs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.emp_classrooms.daos.SchoolDao;
import com.project.emp_classrooms.entities.School;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class SchoolService {
	@Autowired
	SchoolDao schoolDao;

//	Advanced Use cases:	

	
	
	
//	BASIC CRUD:
	
	@PostMapping("/api/school")
	public School createSchool(@RequestBody School school) {
		return schoolDao.createSchool(school);
	}
	
	@GetMapping("/api/school")
	public List<School> findSchools(
			@RequestParam(name="schoolName", required=false) String schoolName) {
		if(schoolName != null) {
			return schoolDao.findSchoolByName(schoolName);
		}
		return schoolDao.findAllSchools();
	}
	
	@GetMapping("/api/school/{sid}")
	public School findSchoolById(@PathVariable("sid") int schoolId) {
		return schoolDao.findSchoolById(schoolId);
	}
		
	@PutMapping("/api/school/{sid}")
	public School updateSchool(
			@PathVariable("sid") int schoolId,
			@RequestBody School updatedSchool) {
		return schoolDao.updateSchool(schoolId, updatedSchool);
	}
	
	@DeleteMapping("/api/school/{sid}")
	public void deleteSchoolById(@PathVariable("sid") int schoolId) {
		schoolDao.deleteSchoolById(schoolId);
	}
}
