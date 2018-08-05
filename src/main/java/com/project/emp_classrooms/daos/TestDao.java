package com.project.emp_classrooms.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.project.emp_classrooms.EmpowerClassroomsApplication;

@Component
public class TestDao implements CommandLineRunner {
	
	@Autowired
	DonorDao donorDao;
	
	@Autowired
	VolunteerDao volunteerDao;
	
	@Autowired
	TeacherDao teacherDao;
	
	@Autowired
	SchoolDao schoolDao;
	
	@Autowired
	ProjectDao projectDao;
	
	public static void main(String[] args) {
		EmpowerClassroomsApplication.main(new String[] {});
	}
	
	@Override
	public void run(String... args) throws Exception {
		donorDao.test();
		volunteerDao.test();
		teacherDao.test();
		schoolDao.test();
		projectDao.test();
	}
	
}
