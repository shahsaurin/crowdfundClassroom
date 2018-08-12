package com.project.emp_classrooms.services.jaxrs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.emp_classrooms.daos.VolunteerDao;
import com.project.emp_classrooms.entities.Teacher;
import com.project.emp_classrooms.entities.Volunteer;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class VolunteerService {
	@Autowired
	VolunteerDao volunteerDao;

//	Advanced Use cases:	

	@PutMapping("/api/volunteer/{vid}/approveProject")
	public void approveProject(
			@PathVariable("vid") int volunteerId,
			@RequestParam(name="projectId", required=true) int projectId) {
		volunteerDao.approveProject(volunteerId, projectId);
	}
	
//	FOR VOLUNTEER LOGIN:
	@PostMapping("/api/volunteer/login")
	public Volunteer findVolunteerByCredentials(@RequestBody Volunteer volunteer) throws Exception {
		return volunteerDao.findVolunteerByCredentials(volunteer.getUsername(), volunteer.getPassword());
	}

	
	
	
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
