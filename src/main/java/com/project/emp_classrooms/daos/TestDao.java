package com.project.emp_classrooms.daos;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.project.emp_classrooms.EmpowerClassroomsApplication;
import com.project.emp_classrooms.entities.Project;
import com.project.emp_classrooms.entities.School;
import com.project.emp_classrooms.entities.Teacher;
import com.project.emp_classrooms.entities.Volunteer;

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
	
	@Autowired
	DonationDao donationDao;
	
	@Autowired
	MessageDao messageDao;
	
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
		donationDao.test();
		messageDao.test();
		
//		Following sequence of working:
		School s1 = new School();
		s1.setName("temp");
		s1.setCity("NYC");
		s1 = schoolDao.createSchool(s1);
		
		Teacher t1 = new Teacher();
		t1.setFirstName("test_teacher");
		t1.setLastName("test_lastN");
		t1.setDob(Date.valueOf("1985-08-19"));
		t1 = teacherDao.createTeacherForSchool(s1.getId(), t1);				
		
		Project p1 = new Project();
		p1.setTitle("Test_proj");
		p1.setSynopsis("test_synopsis");
		p1.setShortDescription("test_desc");
		p1 = projectDao.createProjectForSchool(t1.getId(), p1);
		
		Volunteer v1 = new Volunteer();
		v1.setFirstName("vol_fname");
		v1.setLastName("vol_Lname");
		v1 = volunteerDao.createVolunteer(v1);
		volunteerDao.approveProject(v1.getId(), p1.getId());
		
		
	}
	
}
