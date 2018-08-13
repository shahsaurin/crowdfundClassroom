package com.project.emp_classrooms.services.jaxrs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.emp_classrooms.daos.PersonDao;
import com.project.emp_classrooms.entities.Person;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PersonService {
	
	@Autowired
	PersonDao personDao;
	
	@GetMapping("/api/person/{pid}")
	public Person findPersonById(
			@PathVariable("pid") int personId) {
		return personDao.findPersonById(personId);
	}
	
	@PutMapping("/api/person/{pid}")
	public Person updatePerson(
			@PathVariable("pid") int personId,
			@RequestBody Person person) {
		return personDao.updatePerson(personId, person);
	}
	
	@DeleteMapping("/api/person/{pid}")
	public void deletePerson(@PathVariable("pid") int personId) {
		personDao.deletePerson(personId);
	}
}
