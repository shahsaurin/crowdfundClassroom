package com.project.emp_classrooms.daos;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.emp_classrooms.entities.Teacher;
import com.project.emp_classrooms.repositories.TeacherRepository;

@Component
public class TeacherDao {

	@Autowired
	TeacherRepository teacherRepository;
	
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
	
	void deleteTeacherById(int id) {
		teacherRepository.deleteById(id);
	}
	
	void deleteAllTeachers() {
		teacherRepository.deleteAll();
	}
	
	
	
	public void test() {
//		Delete all teachers:
		deleteAllTeachers();
		
		Teacher t1 = new Teacher();
		t1.setProjectsInitiated(15);
		t1.setDob(Date.valueOf("1995-06-20"));
		t1.setFirstName("tea_1");
		Teacher t2 = createTeacher(t1);
		
		t1.setLastName("teaname");
		updateTeacher(t2.getId(), t1);
		

	}
}
