package com.project.emp_classrooms.daos;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.emp_classrooms.entities.School;
import com.project.emp_classrooms.repositories.SchoolRepository;

@Component
public class SchoolDao {

	@Autowired
	SchoolRepository schoolRepository;
	
	public School createSchool(School school) {
		return schoolRepository.save(school);
	}
	
	public List<School> findAllSchools() {
		return (List<School>) schoolRepository.findAll();
	}
	
	public School findSchoolById(int schoolId) {
		Optional<School> optSchool = schoolRepository.findById(schoolId);
		if(optSchool.isPresent()) {
			return optSchool.get();
		}
		return null;
	}
	
	public School updateSchool(int id, School updatedSchool) {
		Optional<School> optSchool = schoolRepository.findById(id);
		if(optSchool.isPresent()) {
			School oldSchool = optSchool.get();
			oldSchool.setName(updatedSchool.getName());
			oldSchool.setCity(updatedSchool.getCity());
			oldSchool.setZip(updatedSchool.getZip());
			return schoolRepository.save(oldSchool);
		}
		return null;
	}
	
	void deleteSchoolById(int id) {
		schoolRepository.deleteById(id);
	}
	
	void deleteAllSchools() {
		schoolRepository.deleteAll();
	}
	
	
	
	public void test() {
//		Delete all schools:
		deleteAllSchools();
		
		School s1 = new School();
		s1.setName("NEU");
		s1.setCity("BOS");
		s1.setZip("02115");
		
		School s2 = createSchool(s1);
		
		s1.setCity("Boston");
		updateSchool(s2.getId(), s1);
		

	}

}
