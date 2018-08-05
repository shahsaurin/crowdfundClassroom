package com.project.emp_classrooms.daos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.emp_classrooms.entities.Project;
import com.project.emp_classrooms.repositories.ProjectRepository;

@Component
public class ProjectDao {

	@Autowired
	ProjectRepository projectRepository;
	
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
	
	void deleteProjectById(int id) {
		projectRepository.deleteById(id);
	}
	
	void deleteAllProjects() {
		projectRepository.deleteAll();
	}
	
	
	
	public void test() {
//		Delete all projects:
		deleteAllProjects();
		
		Project p1 = new Project();
		p1.setTitle("Laptops needed");
		p1.setShortDescription("Short_description_1");
		p1.setSynopsis("Synopsis_1");
		
		Project p2 = createProject(p1);
		
		p1.setIsApproved(false);
		updateProject(p2.getId(), p1);
		
	}

}
