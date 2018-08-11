package com.project.emp_classrooms.daos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.emp_classrooms.entities.Donation;
import com.project.emp_classrooms.entities.Project;
import com.project.emp_classrooms.entities.School;
import com.project.emp_classrooms.entities.Teacher;
import com.project.emp_classrooms.repositories.DonationRepository;
import com.project.emp_classrooms.repositories.ProjectRepository;
import com.project.emp_classrooms.repositories.SchoolRepository;
import com.project.emp_classrooms.repositories.TeacherRepository;

@Component
public class ProjectDao {

	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	TeacherDao teacherDao;
	
	@Autowired
	SchoolRepository schoolRepository;
	
	@Autowired
	DonationRepository donationRepository;


//	A teacher creates a Project, which becomes a Project of the school he/she is in:
//	Teacher should be already existing in the DB. Project will be created.
	public Project createProjectForSchool(int teacherId, Project project) {
		Teacher teacher = teacherRepository.findById(teacherId).get();
		School school = teacher.getSchool();
		
		project.setCostToComplete(project.getTotalPrice());
		project.setTeacher(teacher);
		project.setSchool(school);
		Project savedProject = projectRepository.save(project);
		
		if(teacher.getProjects() == null) {
			teacher.setProjects(new ArrayList<Project>());
		}
		teacher.getProjects().add(savedProject);
		teacherRepository.save(teacher);
		
		if(school.getProjects() == null) {
			school.setProjects(new ArrayList<Project>());
		}
		school.getProjects().add(savedProject);
		schoolRepository.save(school);
				
		return savedProject;
	
	}
	
//	TESTED OK:
	public List<Project> findAllProjectsByApproval(boolean approvalStatus) {
		List<Project> allProjects = (List<Project>) projectRepository.findAll();
		List<Project> approvedProjects = new ArrayList<Project>();
		for (Iterator<Project> iterator = allProjects.iterator(); iterator.hasNext();) {
			Project project = (Project) iterator.next();
			if(project.getIsApproved() == approvalStatus) {
				approvedProjects.add(project);
			}
		}
		return approvedProjects; 
	}
	
	public List<Project> findAllProjectsBySearchQueryAndApproval(String searchQuery, boolean approvalStatus) {
		List<Project> projectsByApprovals = findAllProjectsByApproval(approvalStatus);
		List<Project> projectsBySearchQuery = new ArrayList<Project>();
		
		for (Iterator<Project> iterator = projectsByApprovals.iterator(); iterator.hasNext();) {
			Project project = (Project) iterator.next();
			if(project.getShortDescription().toLowerCase().contains(searchQuery) || 
					project.getSynopsis().toLowerCase().contains(searchQuery) || 
					project.getTitle().toLowerCase().contains(searchQuery)) {
				
				projectsBySearchQuery.add(project);
			}
		}
		return projectsBySearchQuery;
	}
	
	
	
//	BASIC CRUD:
	
	public Project createProject(Project project) {
		return projectRepository.save(project);
	}
	
	public List<Project> findAllProjects() {
		return (List<Project>) projectRepository.findAll();
	}
	
	public Project findProjectById(int projectId) {
		Optional<Project> optProject = projectRepository.findById(projectId);
		if(optProject.isPresent()) {
			return optProject.get();
		}
		return null;
	}
	
	public Project updateProject(int id, Project updatedProject) {
		Optional<Project> optProject = projectRepository.findById(id);
		if(optProject.isPresent()) {
			Project oldProject = optProject.get();
			oldProject.setTitle(updatedProject.getTitle());
			oldProject.setShortDescription(updatedProject.getShortDescription());
			oldProject.setSynopsis(updatedProject.getSynopsis());
			oldProject.setIsApproved(updatedProject.getIsApproved());
			return projectRepository.save(oldProject);
		}
		return null;
	}
	
//	Deletes only the project:
	public void deleteProjectById(int projectId) {
		Optional<Project> optProject = projectRepository.findById(projectId);
		if(optProject.isPresent()) {
			Project project = optProject.get();
			
			List<Donation> donations = project.getDonations();
			for (Iterator<Donation> iterator = donations.iterator(); iterator.hasNext();) {
				Donation donation = (Donation) iterator.next();
				donation.setProject(null);
				donationRepository.save(donation);
			}
			projectRepository.deleteById(projectId);
		}
	}
	
	public void deleteAllProjects() {
		List<Project> projects = (List<Project>) projectRepository.findAll();
		for (Iterator<Project> iterator = projects.iterator(); iterator.hasNext();) {
			Project project = (Project) iterator.next();
			deleteProjectById(project.getId());
		}
	}
	
	
	
	public void test() {
//		Delete all projects:
		deleteAllProjects();
		
		Project p1 = new Project();
		p1.setTitle("Laptops needed");
		p1.setShortDescription("Short_description_1");
		p1.setSynopsis("Synopsis_1");
		
		p1 = createProject(p1);
		
	}

}
