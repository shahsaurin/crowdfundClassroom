package com.project.emp_classrooms.daos;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.emp_classrooms.entities.Donation;
import com.project.emp_classrooms.entities.Donor;
import com.project.emp_classrooms.entities.Project;
import com.project.emp_classrooms.repositories.DonationRepository;

import ch.qos.logback.core.net.SyslogOutputStream;

@Component
public class DonationDao {

	@Autowired
	DonationRepository donationRepository;
	
	@Autowired
	DonorDao donorDao;
	
	@Autowired
	ProjectDao projectDao;
	
	public void donateToProject(int donorId, int projectId, double amount) {
		Donor donor = donorDao.findDonorById(donorId);
		Project project = projectDao.findProjectById(projectId);

//		Create Donation object:
		Donation donation = new Donation();
		donation.setDonor(donor);
		donation.setProject(project);
		donation.setAmount(amount);
		
//		Donor side updates:
		
//		The 'for' loop check will take care that if a donor has already contributed to a project, and he 
//		contributes to it again, then the 'contributedProjects' field of its table is 'NOT' incremented.
		List<Donation> previousDonations = donor.getDonations();
		Boolean alreadyDonatedToProject = false;
		for (Iterator<Donation> iterator = previousDonations.iterator(); iterator.hasNext();) {
			Donation donation2 = (Donation) iterator.next();
			if(donation2.getDonor().getId() == donorId && donation2.getProject().getId() == projectId) {
				alreadyDonatedToProject = true;
				System.out.println("Enter in: DonorId = " + donorId + " PrjId = " + projectId);
				break;
			}
		}
		if(alreadyDonatedToProject == false) {
			System.out.println("INcrementing:....");
			int oldContributedProjects = donor.getContributedProjects();
			donor.setContributedProjects(oldContributedProjects + 1);
		}
		double oldAmountDonated = donor.getTotalAmountDonated();
		donor.setTotalAmountDonated(oldAmountDonated + amount);
		
		donor.getDonations().add(donation);
		
//		Project side updates:
		project.getDonations().add(donation);
		project.setCostToComplete(project.getCostToComplete() - amount);
		
//		Save all entities to DB:
		donationRepository.save(donation);
		donorDao.createDonor(donor);
		projectDao.createProject(project);	
	}
	
	
	public Donation createDonation(Donation donation) {
		return donationRepository.save(donation);
	}
	
	public List<Donation> findAllDonations() {
		return (List<Donation>) donationRepository.findAll();
	}
	
	public Donation findDonationById(int donationId) {
		Optional<Donation> optDonation = donationRepository.findById(donationId);
		if(optDonation.isPresent()) {
			return optDonation.get();
		}
		return null;
	}
	
	public Donation updateDonation(int id, Donation updatedDonation) {
		Optional<Donation> optDonation = donationRepository.findById(id);
		if(optDonation.isPresent()) {
			Donation oldDonation = optDonation.get();
			oldDonation.setAmount(updatedDonation.getAmount());
			return donationRepository.save(oldDonation);
		}
		return null;
	}
	
	void deleteDonationById(int id) {
		donationRepository.deleteById(id);
	}
	
	void deleteAllDonations() {
		donationRepository.deleteAll();
	}
	
	
	
	public void test() {
//		Delete all donations:
		deleteAllDonations();
		
		Donation d1 = new Donation();
		d1.setAmount(100);
		
		Donation d2 = createDonation(d1);
		
		d1.setAmount(150);
		updateDonation(d2.getId(), d1);
		
	}

}
