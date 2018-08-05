package com.project.emp_classrooms.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.omg.CORBA.DomainManagerOperations;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Donation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JsonIgnore
	private Donor donor;
	
	@ManyToOne
	@JsonIgnore
	private Project project;
}
