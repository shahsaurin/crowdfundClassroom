package com.project.emp_classrooms.services.jaxrs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.emp_classrooms.daos.VolunteerDao;
import com.project.emp_classrooms.entities.Volunteer;

@RestController
public class VolunteerService {
	@Autowired
	VolunteerDao volunteerDao;

//	Advanced Use cases:	

	
	
	
//	BASIC CRUD:
	
	@PostMapping("/api/volunteer")
	public Volunteer createVolunteer(@RequestBody Volunteer volunteer) {
		return volunteerDao.createVolunteer(volunteer);
	}
	
	@GetMapping("/api/volunteer")
	public List<Volunteer> findAllVolunteers() {
		return volunteerDao.findAllVolunteers();
	}
	
	@GetMapping("/api/volunteer/{vid}")
	public Volunteer findVolunteerById(@PathVariable("vid") int volunteerId) {
		return volunteerDao.findVolunteerById(volunteerId);
	}
	
	@PutMapping("/api/volunteer/{vid}")
	public Volunteer updateVolunteer(
			@PathVariable("vid") int volunteerId,
			@RequestBody Volunteer updatedVolunteer) {
		return volunteerDao.updateVolunteer(volunteerId, updatedVolunteer);
	}
	
	@DeleteMapping("/api/volunteer/{vid}")
	public void deleteVolunteerById(@PathVariable("vid") int volunteerId) {
		volunteerDao.deleteVolunteerById(volunteerId);
	}
}
