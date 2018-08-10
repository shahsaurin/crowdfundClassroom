package com.project.emp_classrooms.services.jaxrs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.emp_classrooms.daos.TeacherDao;
import com.project.emp_classrooms.entities.Teacher;

@RestController
public class TeacherService {
	@Autowired
	TeacherDao teacherDao;
	
	
//	Advanced Use cases:	
	
//	Tested OK:
	@PostMapping("/api/teacher")
	public Teacher createTeacherForSchool(
			@RequestParam(name="schoolId", required=true) int schoolId,
			@RequestBody Teacher teacher) {
		return teacherDao.createTeacherForSchool(schoolId, teacher);
	}
	
	
	
//	BASIC CRUD:

//	My app wouldn't support this as per the use cases:
//	@PostMapping("/api/teacher")
//	public Teacher createTeacher(@RequestBody Teacher teacher) {
//		return teacherDao.createTeacher(teacher);
//	}
	
	@GetMapping("/api/teacher")
	public List<Teacher> findAllTeachers() {
		return teacherDao.findAllTeachers();
	}
	
	@GetMapping("/api/teacher/{tid}")
	public Teacher findTeacherById(@PathVariable("tid") int teacherId) {
		return teacherDao.findTeacherById(teacherId);
	}
	
	@PutMapping("/api/teacher/{tid}")
	public Teacher updateTeacher(
			@PathVariable("tid") int teacherId,
			@RequestBody Teacher updatedTeacher) {
		return teacherDao.updateTeacher(teacherId, updatedTeacher);
	}
	
	@DeleteMapping("/api/teacher/{tid}")
	public void deleteTeacherById(@PathVariable("tid") int teacherId) {
		teacherDao.deleteTeacherById(teacherId);
	}

}
