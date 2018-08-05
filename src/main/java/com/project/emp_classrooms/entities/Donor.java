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
	
	public int getContributedProjects() {
		return contributedProjects;
	}

	public void setContributedProjects(int contributedProjects) {
		this.contributedProjects = contributedProjects;
	}

	public int getTotalAmountDonated() {
		return totalAmountDonated;
	}

	public void setTotalAmountDonated(int totalAmountDonated) {
		this.totalAmountDonated = totalAmountDonated;
	}

	public List<Donation> getDonations() {
		return donations;
	}

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}
	
	

}
