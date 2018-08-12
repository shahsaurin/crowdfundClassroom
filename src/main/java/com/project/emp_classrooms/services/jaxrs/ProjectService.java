package com.project.emp_classrooms.services.jaxrs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.emp_classrooms.daos.ProjectDao;
import com.project.emp_classrooms.entities.Project;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProjectService {
	@Autowired
	ProjectDao projectDao;

//	Advanced Use cases:	
	
	@GetMapping("/api/donorschoose/project")
	public ResponseEntity<String> fetchProjectsFromDonorschoose(
			@RequestParam(name="searchQuery", required=true) String searchQuery) {
        return projectDao.fetchProjectsFromDonorschoose(searchQuery);
    }
	

//	Tested OK:
	@PostMapping("/api/project")
	public Project createProjectForSchool(
			@RequestParam(name="teacherId", required=true) int teacherId,
			@RequestBody Project project) {
		return projectDao.createProjectForSchool(teacherId, project);
	}
	
//	To be tested!!: 
//	Find all project: 1) Approved, 2) Non-approved, 3) Approved with search query, 4) Approved without search query,
//	5) Non-approved with search query, 6) Non-approved without search query, 
//	7) All (default/without query params)
//	Example: /api/project?isApproved=true&&searchQuery=lab
	@GetMapping("/api/project")
	public List<Project> findProjects(
			@RequestParam(name="isApproved", required=false) String isApproved,
			@RequestParam(name="searchQuery", required=false) String searchQuery,
			@RequestParam(name="teacherId", required=false) String tId) {
		
		int teacherId;
		if(tId != null) {
			teacherId = Integer.parseInt(tId);
			return projectDao.findAllProjectsByTeacherId(teacherId);
		} else if(isApproved != null && isApproved.equals("true")) {
			if(searchQuery != null) {
				return projectDao.findAllProjectsBySearchQueryAndApproval(searchQuery.toLowerCase(), true);
			}
			return projectDao.findAllProjectsByApproval(true);
		} else if(isApproved != null && isApproved.equals("false")){
			if(searchQuery != null) {
				return projectDao.findAllProjectsBySearchQueryAndApproval(searchQuery.toLowerCase(), false);
			}
			return projectDao.findAllProjectsByApproval(false); 
		} 
		return projectDao.findAllProjects();
	}
	
	
	
//	BASIC CRUD:

//	My app wouldn't support this as per the use cases:
//	@PostMapping("/api/project")
//	public Project createProject(@RequestBody Project project) {
//		return projectDao.createProject(project);
//	}
	
	
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
