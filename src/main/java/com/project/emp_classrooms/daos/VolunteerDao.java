package com.project.emp_classrooms.daos;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.emp_classrooms.entities.Message;
import com.project.emp_classrooms.entities.Project;
import com.project.emp_classrooms.entities.Teacher;
import com.project.emp_classrooms.entities.Volunteer;
import com.project.emp_classrooms.repositories.MessageRepository;
import com.project.emp_classrooms.repositories.ProjectRepository;
import com.project.emp_classrooms.repositories.VolunteerRepository;

@Component
public class VolunteerDao {
	
	@Autowired
	VolunteerRepository volunteerRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	ProjectDao projectDao;
	
	@Autowired
	MessageRepository messageRepository;
	
//	The volunteer and project both should already be existing in the DB. The volunteer sees a list of projects on 
//	his page which he can choose to approve. So the volunteer's ID and the ID of the project he chooses are grabbed.
	public void approveProject(int volunteerId, int projectId) {
		Volunteer volunteer = findVolunteerById(volunteerId);
		Project project = projectDao.findProjectById(projectId);
		
		project.setIsApproved(true);
		project.setVolunteer(volunteer);
//		projectDao.createProject(project);
		
		if(!volunteer.getProjects().contains(project)) {
			volunteer.getProjects().add(project);
			volunteer.setProjectsApproved(volunteer.getProjectsApproved() + 1);
//			volunteerRepository.save(volunteer);
		}
		
		projectDao.createProject(project);
		volunteerRepository.save(volunteer);
	}
	
	
//	BASIC CRUD:
	
	public Volunteer createVolunteer(Volunteer volunteer) {
		return volunteerRepository.save(volunteer);
	}
	
	public List<Volunteer> findAllVolunteers() {
		return (List<Volunteer>) volunteerRepository.findAll();
	}
	
	public Volunteer findVolunteerById(int volunteerId) {
		Optional<Volunteer> optVolunteer = volunteerRepository.findById(volunteerId);
		if(optVolunteer.isPresent()) {
			return optVolunteer.get();
		}
		return null;
	}
	
	public Volunteer findVolunteerByCredentials(String username, String password) throws Exception {
		List<Volunteer> volunteers = (List<Volunteer>) volunteerRepository.findVolunteerByCredentials(username, password);
		if(volunteers.isEmpty()) {
			throw new Exception("Volunteer not found!");
		}
		return volunteers.get(0);
	}
	
	public Volunteer updateVolunteer(int id, Volunteer updatedVolunteer) {
		Optional<Volunteer> optVolunteer = volunteerRepository.findById(id);
		if(optVolunteer.isPresent()) {
			Volunteer oldVolunteer = optVolunteer.get();
			oldVolunteer.setFirstName(updatedVolunteer.getFirstName());
			oldVolunteer.setLastName(updatedVolunteer.getLastName());
			oldVolunteer.setDob(updatedVolunteer.getDob());
			oldVolunteer.setProjectsApproved(updatedVolunteer.getProjectsApproved());
			
			oldVolunteer.setCity(updatedVolunteer.getCity());
			oldVolunteer.setEmail(updatedVolunteer.getEmail());
			oldVolunteer.setUsername(updatedVolunteer.getUsername());
			oldVolunteer.setPassword(updatedVolunteer.getPassword());
			oldVolunteer.setState(updatedVolunteer.getState());
			oldVolunteer.setZip(updatedVolunteer.getZip());
			oldVolunteer.setPhone(updatedVolunteer.getPhone());
			return volunteerRepository.save(oldVolunteer);
		}
		return null;
	}
	
//	Delete only the Volunteer, make fields that refer it as 'NULL':
	public void deleteVolunteerById(int volunteerId) {
		Optional<Volunteer> optVolunteer = volunteerRepository.findById(volunteerId);
		if(optVolunteer.isPresent()) {
			Volunteer volunteer = optVolunteer.get();
			
			List<Project> projects = volunteer.getProjects();
			for (Iterator<Project> iterator = projects.iterator(); iterator.hasNext();) {
				Project project = (Project) iterator.next();
				project.setVolunteer(null);
				projectRepository.save(project);
			}
			
			List<Message> messagesReceived = volunteer.getMessagesReceived();
			for (Iterator<Message> iterator = messagesReceived.iterator(); iterator.hasNext();) {
				Message receivedMessage = (Message) iterator.next();
				receivedMessage.setRecipient(null);
				messageRepository.save(receivedMessage);
			}
			
			List<Message> messagesSent = volunteer.getMessagesSent();
			for (Iterator<Message> iterator = messagesSent.iterator(); iterator.hasNext();) {
				Message sentMessage = (Message) iterator.next();
				sentMessage.setSender(null);;
				messageRepository.save(sentMessage);
			}
			
			volunteerRepository.deleteById(volunteerId);
		}
		
	}
	
	
	public void deleteAllVolunteers() {
		List<Volunteer> volunteers = (List<Volunteer>) volunteerRepository.findAll();
		for (Iterator<Volunteer> iterator = volunteers.iterator(); iterator.hasNext();) {
			Volunteer volunteer = (Volunteer) iterator.next();
			deleteVolunteerById(volunteer.getId());
		}
	}
	
	
	
	public void test() {
//		Delete all volunteers:
//		deleteAllVolunteers();
		
		Volunteer d1 = new Volunteer();
		d1.setProjectsApproved(11);
		d1.setDob(Date.valueOf("1995-05-10"));
		d1.setFirstName("vol_1");
		Volunteer d2 = createVolunteer(d1);
		
		d1.setLastName("volname");
		updateVolunteer(d2.getId(), d1);
		

	}

}
