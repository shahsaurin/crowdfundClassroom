package com.project.emp_classrooms.daos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.emp_classrooms.entities.Person;
import com.project.emp_classrooms.entities.Teacher;
import com.project.emp_classrooms.entities.Volunteer;
import com.project.emp_classrooms.repositories.PersonRepository;

@Component
public class PersonDao {

	@Autowired 
	PersonRepository personRepository;
	
	@Autowired
	TeacherDao teacherDao;
	
	@Autowired
	VolunteerDao volunteerDao;
	
	@Autowired
	DonorDao donorDao;
	
	
	public Person findPersonById(int personId) {
		Optional<Person> optPerson = personRepository.findById(personId);
		if(optPerson.isPresent()) {
			return optPerson.get();
		}
		return null;
	}
	
	public Person updatePerson(int id, Person updatedPerson) {
		Optional<Person> optExistingPerson = personRepository.findById(id);
		if(optExistingPerson.isPresent()) {
			Person existingPerson = optExistingPerson.get();
			existingPerson.setFirstName(updatedPerson.getFirstName());
			existingPerson.setLastName(updatedPerson.getLastName());
			existingPerson.setUsername(updatedPerson.getUsername());
			existingPerson.setPassword(updatedPerson.getPassword());
			existingPerson.setCity(updatedPerson.getCity());
			existingPerson.setDob(updatedPerson.getDob());
			existingPerson.setEmail(updatedPerson.getEmail());
			existingPerson.setState(updatedPerson.getState());
			existingPerson.setPhone(updatedPerson.getPhone());
			existingPerson.setZip(updatedPerson.getZip());
			return personRepository.save(existingPerson);
		}
		return null;
	}
	
	public void deletePerson(int id) {
		Optional<Person> optPerson = personRepository.findById(id);
		if(optPerson.isPresent()) {
			Person person = optPerson.get();
			if(person instanceof Teacher) {
				teacherDao.deleteTeacherById(id);
			} else if(person instanceof Volunteer) {
				volunteerDao.deleteVolunteerById(id);
			} else {
				donorDao.deleteDonorById(id);
			}
		}
	}
		
	public void test() {
//		Sample tests for this DAO that are executed from the Java application is run:
	}
}
