package com.project.emp_classrooms.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class School {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String city;
	private String zip;
	
	@OneToMany(mappedBy="school")
	private List<Teacher> teachers;
	
	@OneToMany(mappedBy="school")
	private List<Project> projects;
	
}
