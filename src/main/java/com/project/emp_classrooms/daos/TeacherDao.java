package com.project.emp_classrooms.daos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.emp_classrooms.entities.School;
import com.project.emp_classrooms.entities.Teacher;
import com.project.emp_classrooms.repositories.SchoolRepository;
import com.project.emp_classrooms.repositories.TeacherRepository;

@Component
public class TeacherDao {

	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	SchoolRepository schoolRepository;
	
//	For use on web app: On create 'New Teacher' page.
//	Assumes that school already exists on DB (found by 'name'), teacher will be created.
//	When teacher creates on save button after filling up the form, the school ID is found first by name entered in 
//	the school name field in the form. Then for that school ID the teacher will be created.
//	***** TESTED OK ****
	public Teacher createTeacherForSchool(int schoolId, Teacher teacher) {
		School school = schoolRepository.findById(schoolId).get();
		
		teacher.setSchool(school);
		Teacher savedTeacher = teacherRepository.save(teacher);
		
		if(school.getTeachers() == null) {
			school.setTeachers(new ArrayList<Teacher>());
		} 
		school.getTeachers().add(teacher);
		schoolRepository.save(school);
		return savedTeacher;		
	}
	
	public Teacher createTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}
	
	public List<Teacher> findAllTeachers() {
		return (List<Teacher>) teacherRepository.findAll();
	}
	
	public Teacher findTeacherById(int teacherId) {
		Optional<Teacher> optTeacher = teacherRepository.findById(teacherId);
		if(optTeacher.isPresent()) {
			return optTeacher.get();
		}
		return null;
	}
	
	public Teacher updateTeacher(int id, Teacher updatedTeacher) {
		Optional<Teacher> optTeacher = teacherRepository.findById(id);
		if(optTeacher.isPresent()) {
			Teacher oldTeacher = optTeacher.get();
			oldTeacher.setFirstName(updatedTeacher.getFirstName());
			oldTeacher.setLastName(updatedTeacher.getLastName());
			oldTeacher.setDob(updatedTeacher.getDob());
			oldTeacher.setProjectsInitiated(updatedTeacher.getProjectsInitiated());
			return teacherRepository.save(oldTeacher);
		}
		return null;
	}
	
	public void deleteTeacherById(int id) {
		teacherRepository.deleteById(id);
	}
	
	public void deleteAllTeachers() {
		teacherRepository.deleteAll();
	}
	
	
	
	public void test() {
//		Delete all teachers:
		deleteAllTeachers();
		
		Teacher t1 = new Teacher();
		t1.setProjectsInitiated(15);
		t1.setDob(Date.valueOf("1995-06-20"));
		t1.setFirstName("tea_1");
		t1 = createTeacher(t1);
		
		t1.setLastName("teaname");
		updateTeacher(t1.getId(), t1);

	}
}
