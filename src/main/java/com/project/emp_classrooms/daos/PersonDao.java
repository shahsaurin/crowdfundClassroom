package com.project.emp_classrooms.daos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.emp_classrooms.entities.Person;
import com.project.emp_classrooms.repositories.PersonRepository;

@Component
public class PersonDao {

	@Autowired 
	PersonRepository personRepository;
	
	
	public Person findPersonById(int personId) {
		Optional<Person> optPerson = personRepository.findById(personId);
		if(optPerson.isPresent()) {
			return optPerson.get();
		}
		return null;
	}
	
		
	public void test() {

	}
}
