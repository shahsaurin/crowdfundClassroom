package com.project.emp_classrooms.daos;

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
//		adminDao.deleteAllAdmins();
//		donorDao.deleteAllDonors();
//		volunteerDao.deleteAllVolunteers();
//		teacherDao.deleteAllTeachers();
//		schoolDao.deleteAllSchools();
//		projectDao.deleteAllProjects();
//		donationDao.deleteAllDonations();
//		messageDao.deleteAllMessages();
//		
//		adminDao.test();
//		donorDao.test();
//		volunteerDao.test();
//		teacherDao.test();
//		schoolDao.test();
//		projectDao.test();
//		donationDao.test();
//		messageDao.test();
//		
////		Following sequence of working:
//		School s1 = new School();
//		s1.setName("NEU");
//		s1.setZip("02115");
//		s1.setCity("Boston");
//		s1 = schoolDao.createSchool(s1);
//		
//		School s2 = new School();
//		s2.setName("NYU");
//		s2.setCity("NYC");
//		s2.setZip("34567");
//		s2 = schoolDao.createSchool(s2);
//		
//		Teacher t1 = new Teacher();
//		t1.setFirstName("test_teacher");
//		t1.setLastName("test_lastN");
//		t1.setUsername("t");
//		t1.setPassword("tt");
//		t1.setCity("Los Angeles");
//		t1.setEmail("temp@email.com");
//		t1.setPhone("123456789");
//		t1.setState("California");
//		t1.setZip("12345");
////		t1.setDob(Date.valueOf("1985-08-19"));
//		t1 = teacherDao.createTeacherForSchool(s1.getId(), t1);				
//		
//		Project p1 = new Project();
//		p1.setTitle("Test_proj_1");
//		p1.setSynopsis("test_synopsis_1");
//		p1.setShortDescription("test_desc_1");
//		p1.setTotalPrice(1000.00);
//		p1.setCostToComplete(1000.00);
//		p1 = projectDao.createProjectForSchool(t1.getId(), p1);
//		
//		Project p2 = new Project();
//		p2.setTitle("Test_proj_2");
//		p2.setSynopsis("test_synopsis_2");
//		p2.setShortDescription("test_desc_2");
//		p2.setTotalPrice(900.00);
//		p2.setCostToComplete(900.00);
//		p2 = projectDao.createProjectForSchool(t1.getId(), p2);
//		
//		Volunteer v1 = new Volunteer();
//		v1.setFirstName("vol_Fname");
//		v1.setLastName("vol_Lname");
//		v1.setUsername("v");
//		v1.setPassword("vv");
//		v1.setCity("Chicago");
//		v1.setEmail("vol@email.com");
//		v1.setPhone("123456789");
//		v1.setState("Illinois");
//		v1.setZip("12345");
//		v1 = volunteerDao.createVolunteer(v1);
//		
//		volunteerDao.approveProject(v1.getId(), p1.getId());
//		volunteerDao.approveProject(v1.getId(), p2.getId());
//		
//		Donor d1 = new Donor();
//		d1.setFirstName("testDonor1");
//		d1.setLastName("testD_Lname1");
//		d1.setUsername("d");
//		d1.setPassword("dd");
//		d1.setCity("Detroit");
//		d1.setEmail("donor@email.com");
//		d1.setPhone("2468013579");
//		d1.setState("Michigan");
//		d1.setZip("67890");
//		d1 = donorDao.createDonor(d1);
//		
//		Teacher t2 = new Teacher();
//		t2.setFirstName("Alice");
//		t2.setLastName("Wonderland");
//		t2.setUsername("alice");
//		t2.setPassword("alice");
//		t2.setCity("NYC");
//		t2.setEmail("alice@email.com");
//		t2.setPhone("123456789");
//		t2.setState("New York");
//		t2.setZip("12345");
//		t2 = teacherDao.createTeacherForSchool(s2.getId(), t2);
//		
//		Volunteer v2 = new Volunteer();
//		v2.setFirstName("Bob");
//		v2.setLastName("Marley");
//		v2.setUsername("bob");
//		v2.setPassword("bob");
//		v2.setCity("Dallas");
//		v2.setEmail("bob@email.com");
//		v2.setPhone("987654321");
//		v2.setState("Texas");
//		v2.setZip("23456");
//		v2 = volunteerDao.createVolunteer(v2);
//		
//		Donor d2 = new Donor();
//		d2.setFirstName("Charles");
//		d2.setLastName("Garcia");
//		d2.setUsername("charlie");
//		d2.setPassword("charlie");
//		d2.setCity("San Jose");
//		d2.setEmail("charlie@email.com");
//		d2.setPhone("1122334455");
//		d2.setState("California");
//		d2.setZip("13579");
//		d2 = donorDao.createDonor(d2);
		
	}
	
}
