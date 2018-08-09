package com.project.emp_classrooms.daos;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.emp_classrooms.entities.Project;
import com.project.emp_classrooms.entities.Volunteer;
import com.project.emp_classrooms.repositories.VolunteerRepository;

@Component
public class VolunteerDao {
	
	@Autowired
	VolunteerRepository volunteerRepository;
	
	@Autowired
	ProjectDao projectDao;
	
//	The volunteer and project both should already be existing in the DB. The volunteer sees a list of projects on 
//	his page which he can choose to approve. So the volunteer's ID and the ID of the project he chooses are grabbed.
	public void approveProject(int volunteerId, int projectId) {
		Volunteer volunteer = findVolunteerById(volunteerId);
		Project project = projectDao.findProjectById(projectId);
		
		project.setIsApproved(true);
		project.setVolunteer(volunteer);
		projectDao.createProject(project);
		
		if(!volunteer.getProjects().contains(project)) {
			volunteer.getProjects().add(project);
			volunteerRepository.save(volunteer);
		}
	}
	
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
	
	public Volunteer updateVolunteer(int id, Volunteer updatedVolunteer) {
		Optional<Volunteer> optVolunteer = volunteerRepository.findById(id);
		if(optVolunteer.isPresent()) {
			Volunteer oldVolunteer = optVolunteer.get();
			oldVolunteer.setFirstName(updatedVolunteer.getFirstName());
			oldVolunteer.setLastName(updatedVolunteer.getLastName());
			oldVolunteer.setDob(updatedVolunteer.getDob());
			oldVolunteer.setProjectsApproved(updatedVolunteer.getProjectsApproved());
			return volunteerRepository.save(oldVolunteer);
		}
		return null;
	}
	
	void deleteVolunteerById(int id) {
		volunteerRepository.deleteById(id);
	}
	
	void deleteAllVolunteers() {
		volunteerRepository.deleteAll();
	}
	
	
	
	public void test() {
//		Delete all volunteers:
		deleteAllVolunteers();
		
		Volunteer d1 = new Volunteer();
		d1.setProjectsApproved(11);
		d1.setDob(Date.valueOf("1995-05-10"));
		d1.setFirstName("vol_1");
		Volunteer d2 = createVolunteer(d1);
		
		d1.setLastName("volname");
		updateVolunteer(d2.getId(), d1);
		

	}

}
