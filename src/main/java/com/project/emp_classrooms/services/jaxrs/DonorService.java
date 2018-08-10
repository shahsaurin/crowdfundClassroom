package com.project.emp_classrooms.services.jaxrs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.emp_classrooms.daos.DonorDao;
import com.project.emp_classrooms.entities.Donor;

@RestController
public class DonorService {

	@Autowired
	DonorDao donorDao;
	
//	Advanced Use cases:	
	
//	Nothing here for now:
	
	
//	BASIC CRUD:
	
	@PostMapping("/api/donor")
	public Donor createDonor(@RequestBody Donor donor) {
		return donorDao.createDonor(donor);
	}
	
	@GetMapping("/api/donor")
	public List<Donor> findAllDonors() {
		return donorDao.findAllDonors();
	}
	
	@GetMapping("/api/donor/{did}")
	public Donor findDonorById(@PathVariable("did") int donorId) {
		return donorDao.findDonorById(donorId);
	}
	
	@PutMapping("/api/donor/{did}")
	public Donor updateDonor(
			@PathVariable("did") int donorId,
			@RequestBody Donor updatedDonor) {
		return donorDao.updateDonor(donorId, updatedDonor);
	}
	
	@DeleteMapping("/api/donor/{did}")
	public void deleteDonorById(@PathVariable("did") int donorId) {
		donorDao.deleteDonorById(donorId);
	}
}
