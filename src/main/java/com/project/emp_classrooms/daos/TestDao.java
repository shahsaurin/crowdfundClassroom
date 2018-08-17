package com.project.emp_classrooms.daos;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.project.emp_classrooms.EmpowerClassroomsApplication;
import com.project.emp_classrooms.entities.Donor;
import com.project.emp_classrooms.entities.Project;
import com.project.emp_classrooms.entities.School;
import com.project.emp_classrooms.entities.Teacher;
import com.project.emp_classrooms.entities.Volunteer;

@Component
public class TestDao implements CommandLineRunner {
	
	@Autowired
	AdminDao adminDao;
	
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
		adminDao.deleteAllAdmins();
		donorDao.deleteAllDonors();
		volunteerDao.deleteAllVolunteers();
		teacherDao.deleteAllTeachers();
		schoolDao.deleteAllSchools();
		projectDao.deleteAllProjects();
		donationDao.deleteAllDonations();
		messageDao.deleteAllMessages();
		
		adminDao.test();
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
		p1.setTitle("Test_proj_1");
		p1.setSynopsis("test_synopsis_1");
		p1.setShortDescription("test_desc_1");
		p1.setTotalPrice(1000.00);
		p1.setCostToComplete(1000.00);
		p1 = projectDao.createProjectForSchool(t1.getId(), p1);
		
		Project p2 = new Project();
		p2.setTitle("Test_proj_2");
		p2.setSynopsis("test_synopsis_2");
		p2.setShortDescription("test_desc_2");
		p2.setTotalPrice(900.00);
		p2.setCostToComplete(900.00);
		p2 = projectDao.createProjectForSchool(t1.getId(), p2);
		
		Volunteer v1 = new Volunteer();
		v1.setFirstName("vol_fname");
		v1.setLastName("vol_Lname");
		v1 = volunteerDao.createVolunteer(v1);
		
		volunteerDao.approveProject(v1.getId(), p1.getId());
		volunteerDao.approveProject(v1.getId(), p2.getId());
		
		Donor d1 = new Donor();
		d1.setFirstName("testDonor1");
		d1.setLastName("testD_Lname1");
		d1 = donorDao.createDonor(d1);
		
		Donor d2 = new Donor();
		d2.setFirstName("testDonor2");
		d2.setLastName("testD_Lname2");
		d2 = donorDao.createDonor(d2);
		
//		donationDao.donateToProject(d1.getId(), p1.getId(), 230.0);
//		donationDao.donateToProject(d2.getId(), p1.getId(), 110.0);
//		donationDao.donateToProject(d1.getId(), p1.getId(), 50.0);
//		donationDao.donateToProject(d1.getId(), p2.getId(), 90.0);
		
	}
	
}
