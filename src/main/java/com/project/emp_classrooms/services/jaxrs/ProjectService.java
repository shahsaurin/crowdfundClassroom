package com.project.emp_classrooms.services.jaxrs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.emp_classrooms.daos.ProjectDao;
import com.project.emp_classrooms.entities.Project;

public class ProjectService {
	@Autowired
	ProjectDao projectDao;

//	Advanced Use cases:	

	
	
	
//	BASIC CRUD:
	
	@PostMapping("/api/project")
	public Project createProject(@RequestBody Project project) {
		return projectDao.createProject(project);
	}
	
	@GetMapping("/api/project")
	public List<Project> findAllProjects() {
		return projectDao.findAllProjects();
	}
	
	@GetMapping("/api/project/{pid}")
	public Project findProjectById(@PathVariable("pid") int projectId) {
		return projectDao.findProjectById(projectId);
	}
	
	@PutMapping("/api/project/{pid}")
	public Project updateProject(
			@PathVariable("pid") int projectId,
			@RequestBody Project updatedProject) {
		return projectDao.updateProject(projectId, updatedProject);
	}
	
	@DeleteMapping("/api/project/{pid}")
	public void deleteProjectById(@PathVariable("pid") int projectId) {
		projectDao.deleteProjectById(projectId);
	}
}
