package com.project.emp_classrooms.services.jaxrs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.emp_classrooms.daos.DonationDao;
import com.project.emp_classrooms.entities.Donation;

@RestController
public class DonationService {

	@Autowired
	DonationDao donationDao;

//	Advanced Use cases:	
	@PostMapping("/api/donor/{did}/project/{pid}/donate")
	public void donateToProject(
			@PathVariable("did") int donorId,
			@PathVariable("pid") int projectId,
			@RequestParam(name="amount", required=true) double amount) {
		donationDao.donateToProject(donorId, projectId, amount);
	}
 		
	
//	BASIC CRUD:
	
//	My app wouldn't support this as per the use cases:
//	@PostMapping("/api/donation")
//	public Donation createDonation(@RequestBody Donation donation) {
//		return donationDao.createDonation(donation);
//	}
	
	@GetMapping("/api/donation")
	public List<Donation> findAllDonations() {
		return donationDao.findAllDonations();
	}
	
	@GetMapping("/api/donation/{did}")
	public Donation findDonationById(@PathVariable("did") int donationId) {
		return donationDao.findDonationById(donationId);
	}
	
	@PutMapping("/api/donation/{did}")
	public Donation updateDonation(
			@PathVariable("did") int donationId,
			@RequestBody Donation updatedDonation) {
		return donationDao.updateDonation(donationId, updatedDonation);
	}
	
	@DeleteMapping("/api/donation/{did}")
	public void deleteDonationById(@PathVariable("did") int donationId) {
		donationDao.deleteDonationById(donationId);
	}
}
