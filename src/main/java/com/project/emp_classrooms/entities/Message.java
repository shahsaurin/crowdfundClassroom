package com.project.emp_classrooms.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.emp_classrooms.enums.User;

@Entity
public class Message {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String description;
	
//	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp						//TRY USING THIS IF previous annotation DOESN'T WORK
	private Date lastUpdated;
	
	@Enumerated
	@Column(columnDefinition="smallint")
	private User messageCategory;			// 'User' is an 'Enum' and not an entity (class)	
	
	@ManyToOne
	@JsonIgnore
	private Person recipient;				// Reference of type of parent class
	
	@ManyToOne
	@JsonIgnore
	private Person sender;					// Reference of type of parent class

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public User getMessageCategory() {
		return messageCategory;
	}

	public void setMessageCategory(User messageCategory) {
		this.messageCategory = messageCategory;
	}

	public Person getRecipient() {
		return recipient;
	}

	public void setRecipient(Person recipient) {
		this.recipient = recipient;
	}

	public Person getSender() {
		return sender;
	}

	public void setSender(Person sender) {
		this.sender = sender;
	}
		
	
}
