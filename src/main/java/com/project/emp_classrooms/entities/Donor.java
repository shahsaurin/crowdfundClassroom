package com.project.emp_classrooms.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
//@Inheritance(strategy=InheritanceType.JOINED)
public class Donor extends Person {
	
	private int contributedProjects;
	private double totalAmountDonated;
	
	@OneToMany(mappedBy="donor")
	private List<Donation> donations;
	
//	Default constructor:
	public Donor() {
		super();
		donations = new ArrayList<Donation>();
	}
	
	
	public int getContributedProjects() {
		return contributedProjects;
	}

	public void setContributedProjects(int contributedProjects) {
		this.contributedProjects = contributedProjects;
	}

	public double getTotalAmountDonated() {
		return totalAmountDonated;
	}

	public void setTotalAmountDonated(double totalAmountDonated) {
		this.totalAmountDonated = totalAmountDonated;
	}

	public List<Donation> getDonations() {
		return donations;
	}

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}
	
	

}
