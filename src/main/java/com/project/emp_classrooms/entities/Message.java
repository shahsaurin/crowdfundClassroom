package com.project.emp_classrooms.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.emp_classrooms.enums.User;

@Entity
public class Message {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String description;
	private Date date;
	
	@Enumerated
	@Column(columnDefinition="smallint")
	private User messageCategory;			// 'User' is an 'Enum' and not an entity (class)	
	
	@ManyToOne
	@JsonIgnore
	private Person recipient;				// Reference of type of parent class
	
	@ManyToOne
	@JsonIgnore
	private Person sender;					// Reference of type of parent class
	
	
	
}
