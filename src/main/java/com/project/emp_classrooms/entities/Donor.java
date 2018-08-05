package com.project.emp_classrooms.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Donor extends Person {
	
	private int contributedProjects;
	private int totalAmountDonated;
	
	@OneToMany(mappedBy="donor")
	private List<Donation> donations;

}
