package com.project.emp_classrooms.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Volunteer extends Person{
	
	@OneToMany(mappedBy="volunteer")
	private List<Project> approvedProjects;
	
	private String tempFieldDelete;
	
}
