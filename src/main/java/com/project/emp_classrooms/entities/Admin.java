package com.project.emp_classrooms.entities;

import javax.persistence.Entity;

@Entity
public class Admin extends Person {

	private boolean isSuperAdmin;

	public boolean isSuperAdmin() {
		return isSuperAdmin;
	}

	public void setSuperAdmin(boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}
	
	
}
