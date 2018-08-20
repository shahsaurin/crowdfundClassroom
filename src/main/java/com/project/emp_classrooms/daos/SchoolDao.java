package com.project.emp_classrooms.daos;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.emp_classrooms.entities.Project;
import com.project.emp_classrooms.entities.School;
import com.project.emp_classrooms.entities.Teacher;
import com.project.emp_classrooms.repositories.SchoolRepository;
import com.project.emp_classrooms.repositories.TeacherRepository;

@Component
public class SchoolDao {

	@Autowired
	SchoolRepository schoolRepository;
	
	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	ProjectDao projectDao;
	
	@Autowired
	TeacherDao teacherDao;
	
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
	
	public List<School> findSchoolByName(String name) {
		return schoolRepository.findSchoolsByName(name);
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
	
//	Delete the school, all its teachers, all its projects.........'Donation' stays.
	public void deleteSchoolById(int schoolId) {
		Optional<School> optSchool = schoolRepository.findById(schoolId);
		if(optSchool.isPresent()) {
			School school = optSchool.get();
			
			List<Project> projects = school.getProjects();
			for (Iterator<Project> iterator = projects.iterator(); iterator.hasNext();) {
				Project project = (Project) iterator.next();
				projectDao.deleteProjectById(project.getId());
			}
			
			List<Teacher> teachers = school.getTeachers();
			for (Iterator<Teacher> iterator = teachers.iterator(); iterator.hasNext();) {
				Teacher teacher = (Teacher) iterator.next();
				teacherDao.deleteTeacherById(teacher.getId());
			}
			schoolRepository.deleteById(schoolId);
		}
	}
	
	public void deleteAllSchools() {
		List<School> schools = (List<School>) schoolRepository.findAll();
		for (Iterator<School> iterator = schools.iterator(); iterator.hasNext();) {
			School school = (School) iterator.next();
			deleteSchoolById(school.getId());
		}
	}
	
	
	
	public void test() {
//		Sample tests for this DAO that are executed from the Java application is run:
		
	}

}
