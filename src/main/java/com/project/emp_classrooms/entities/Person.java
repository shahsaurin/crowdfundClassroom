package com.project.emp_classrooms.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn
public class Person {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private Date dob;
	
	@OneToMany(mappedBy="recipient")
	private List<Message> messagesReceived;					// CONFIRM IF THIS IS A GOOD WAY TO DO
	
	@OneToMany(mappedBy="sender")
	private List<Message> messagesSent;						// CONFIRM IF THIS IS A GOOD WAY TO DO
	
}
