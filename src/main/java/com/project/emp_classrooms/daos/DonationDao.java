package com.project.emp_classrooms.daos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.emp_classrooms.entities.Donation;
import com.project.emp_classrooms.repositories.DonationRepository;

@Component
public class DonationDao {

	@Autowired
	DonationRepository donationRepository;
	
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
