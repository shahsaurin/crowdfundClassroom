package com.project.emp_classrooms.daos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.emp_classrooms.entities.Message;
import com.project.emp_classrooms.entities.Project;
import com.project.emp_classrooms.entities.School;
import com.project.emp_classrooms.entities.Teacher;
import com.project.emp_classrooms.repositories.MessageRepository;
import com.project.emp_classrooms.repositories.ProjectRepository;
import com.project.emp_classrooms.repositories.SchoolRepository;
import com.project.emp_classrooms.repositories.TeacherRepository;

@Component
public class TeacherDao {

	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	SchoolRepository schoolRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	MessageRepository messageRepository;
	
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
	
	
//	BASIC CRUD:
	
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
	
	public Teacher findTeacherByCredentials(String username, String password) throws Exception {
		List<Teacher> teachers = (List<Teacher>) teacherRepository.findTeacherByCredentials(username, password);
		if(teachers.isEmpty()) {
			throw new Exception("User not found!");
		}
		return teachers.get(0);
	}
	
	public Teacher updateTeacher(int id, Teacher updatedTeacher) {
		Optional<Teacher> optTeacher = teacherRepository.findById(id);
		if(optTeacher.isPresent()) {
			Teacher oldTeacher = optTeacher.get();
			oldTeacher.setFirstName(updatedTeacher.getFirstName());
			oldTeacher.setLastName(updatedTeacher.getLastName());
			oldTeacher.setDob(updatedTeacher.getDob());
			oldTeacher.setProjectsInitiated(updatedTeacher.getProjectsInitiated());
			oldTeacher.setCity(updatedTeacher.getCity());
			oldTeacher.setEmail(updatedTeacher.getEmail());
			oldTeacher.setUsername(updatedTeacher.getUsername());
			oldTeacher.setPassword(updatedTeacher.getPassword());
			oldTeacher.setState(updatedTeacher.getState());
			oldTeacher.setZip(updatedTeacher.getZip());
			oldTeacher.setPhone(updatedTeacher.getPhone());
			
			return teacherRepository.save(oldTeacher);
		}
		return null;
	}
	
//	Deletes only the teacher:
	public void deleteTeacherById(int teacherId) {
		Optional<Teacher> optTeacher = teacherRepository.findById(teacherId);
		if(optTeacher.isPresent()) {
			Teacher teacher = optTeacher.get();
			
			List<Project> projects = teacher.getProjects();
			for (Iterator<Project> iterator = projects.iterator(); iterator.hasNext();) {
				Project project = (Project) iterator.next();
				project.setTeacher(null);
				projectRepository.save(project);
			}
			
			List<Message> messagesReceived = teacher.getMessagesReceived();
			for (Iterator<Message> iterator = messagesReceived.iterator(); iterator.hasNext();) {
				Message receivedMessage = (Message) iterator.next();
				receivedMessage.setRecipient(null);
				messageRepository.save(receivedMessage);
			}
			
			List<Message> messagesSent = teacher.getMessagesSent();
			for (Iterator<Message> iterator = messagesSent.iterator(); iterator.hasNext();) {
				Message sentMessage = (Message) iterator.next();
				sentMessage.setSender(null);;
				messageRepository.save(sentMessage);
			}
			
			teacherRepository.deleteById(teacherId);
		}
	}
	
	public void deleteAllTeachers() {
		List<Teacher> teachers = (List<Teacher>) teacherRepository.findAll();
		for (Iterator<Teacher> iterator = teachers.iterator(); iterator.hasNext();) {
			Teacher teacher = (Teacher) iterator.next();
			deleteTeacherById(teacher.getId());
		}
	}
	
	
	
	public void test() {
//		Delete all teachers:
//		deleteAllTeachers();
		
		Teacher t1 = new Teacher();
		t1.setProjectsInitiated(15);
		t1.setDob(Date.valueOf("1995-06-20"));
		t1.setFirstName("tea_1");
		t1 = createTeacher(t1);
		
		t1.setLastName("teaname");
		updateTeacher(t1.getId(), t1);

	}
}
