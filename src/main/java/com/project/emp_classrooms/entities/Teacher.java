package com.project.emp_classrooms.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Teacher extends Person {

	private int projectsInitiated;		// How if INSTEAD ADDED A LIST OF PROJECTS BELOW THE TEACHER HAS INITIATED
//	private int projectsReceivedApproval;
	
	@ManyToOne
	@JsonIgnore
	private School school;
	
	
	@OneToMany(mappedBy="teacher")
	private List<Project> projects;

	public int getProjectsInitiated() {
		return projectsInitiated;
	}

	public void setProjectsInitiated(int projectsInitiated) {
		this.projectsInitiated = projectsInitiated;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	
	
}
