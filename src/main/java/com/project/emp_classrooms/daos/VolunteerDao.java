package com.project.emp_classrooms.daos;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.emp_classrooms.entities.Volunteer;
import com.project.emp_classrooms.repositories.VolunteerRepository;

@Component
public class VolunteerDao {
	
	@Autowired
	VolunteerRepository volunteerRepository;
	
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
