package com.project.emp_classrooms.daos;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.emp_classrooms.entities.Donation;
import com.project.emp_classrooms.entities.Donor;
import com.project.emp_classrooms.entities.Project;
import com.project.emp_classrooms.repositories.DonorRepository;

@Component
public class DonorDao {
	
	@Autowired
	DonorRepository donorRepository;
	
	@Autowired
	ProjectDao projectDao;
	
	
	public Donor createDonor(Donor donor) {
		return donorRepository.save(donor);
	}
	
	public List<Donor> findAllDonors() {
		return (List<Donor>) donorRepository.findAll();
	}
	
	public Donor findDonorById(int donorId) {
		Optional<Donor> optDonor = donorRepository.findById(donorId);
		if(optDonor.isPresent()) {
			return optDonor.get();
		}
		return null;
	}
	
	public Donor updateDonor(int id, Donor updatedDonor) {
		Optional<Donor> optDonor = donorRepository.findById(id);
		if(optDonor.isPresent()) {
			Donor oldDonor = optDonor.get();
			oldDonor.setFirstName(updatedDonor.getFirstName());
			oldDonor.setLastName(updatedDonor.getLastName());
			oldDonor.setDob(updatedDonor.getDob());
			oldDonor.setContributedProjects(updatedDonor.getContributedProjects());
			oldDonor.setTotalAmountDonated(updatedDonor.getTotalAmountDonated());
			return donorRepository.save(oldDonor);
		}
		return null;
	}
	
	void deleteDonorById(int id) {
		donorRepository.deleteById(id);
	}
	
	void deleteAllDonors() {
		donorRepository.deleteAll();
	}
	
	
	
	public void test() {
//		Delete all donors:
		deleteAllDonors();
		
		Donor d1 = new Donor();
		d1.setContributedProjects(7);
		d1.setDob(Date.valueOf("1993-06-10"));
		d1.setFirstName("donor1");
		Donor d2 = createDonor(d1);
		
		d1.setLastName("donor_lname");
		updateDonor(d2.getId(), d1);
		

	}
}
