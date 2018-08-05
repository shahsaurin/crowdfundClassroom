package com.project.emp_classrooms.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Message {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String description;
	private Date date;
	
	@ManyToOne
	@JsonIgnore
	private Person recipient;				// Reference of type of parent class
	
	@ManyToOne
	@JsonIgnore
	private Person sender;					// Reference of type of parent class
	
	
	
}
