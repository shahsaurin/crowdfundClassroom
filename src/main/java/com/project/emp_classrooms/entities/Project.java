package com.project.emp_classrooms.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	private String shortDescription;
	private String synopsis;
	private Boolean isApproved;
	
	@ManyToOne
	@JsonIgnore
	private Teacher teacher;
	
	@ManyToOne
	@JsonIgnore
	private Volunteer volunteer;
	
	@ManyToOne
	@JsonIgnore
	private School school;
	
	@OneToMany(mappedBy="project")
	private List<Donation> donations;
	
	
	
	
	
}
