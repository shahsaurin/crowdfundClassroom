package com.project.emp_classrooms.daos;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.emp_classrooms.entities.Donation;
import com.project.emp_classrooms.entities.Donor;
import com.project.emp_classrooms.entities.Message;
import com.project.emp_classrooms.entities.Teacher;
import com.project.emp_classrooms.repositories.DonationRepository;
import com.project.emp_classrooms.repositories.DonorRepository;
import com.project.emp_classrooms.repositories.MessageRepository;

@Component
public class DonorDao {
	
	@Autowired
	DonorRepository donorRepository;
	
	@Autowired
	ProjectDao projectDao;
	
	@Autowired
	DonationRepository donationRepository;
	
	@Autowired
	MessageRepository messageRepository;
	
	
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
	
	public Donor findDonorByCredentials(String username, String password) throws Exception {
		List<Donor> donors = (List<Donor>) donorRepository.findDonorByCredentials(username, password);
		if(donors.isEmpty()) {
			throw new Exception("User not found!");
		}
		return donors.get(0);
	}
	
	public Donor updateDonor(int id, Donor updatedDonor) {
		Optional<Donor> optDonor = donorRepository.findById(id);
		if(optDonor.isPresent()) {
			Donor oldDonor = optDonor.get();
			oldDonor.setFirstName(updatedDonor.getFirstName());
			oldDonor.setLastName(updatedDonor.getLastName());
			oldDonor.setDob(updatedDonor.getDob());
			oldDonor.setCity(updatedDonor.getCity());
			oldDonor.setEmail(updatedDonor.getEmail());
			oldDonor.setUsername(updatedDonor.getUsername());
			oldDonor.setPassword(updatedDonor.getPassword());
			oldDonor.setState(updatedDonor.getState());
			oldDonor.setZip(updatedDonor.getZip());
			oldDonor.setPhone(updatedDonor.getPhone());
			
//			oldDonor.setContributedProjects(updatedDonor.getContributedProjects());
//			oldDonor.setTotalAmountDonated(updatedDonor.getTotalAmountDonated());
			return donorRepository.save(oldDonor);
		}
		return null;
	}
	
	public void deleteDonorById(int donorId) {
		Optional<Donor> optDonor = donorRepository.findById(donorId);
		if(optDonor.isPresent()) {
			Donor donor = optDonor.get();
			
			List<Donation> donations = donor.getDonations();
			for (Iterator<Donation> iterator = donations.iterator(); iterator.hasNext();) {
				Donation donation = (Donation) iterator.next();
				donation.setDonor(null);
				donationRepository.save(donation);
			}
			
			List<Message> messagesReceived = donor.getMessagesReceived();
			for (Iterator<Message> iterator = messagesReceived.iterator(); iterator.hasNext();) {
				Message receivedMessage = (Message) iterator.next();
				receivedMessage.setRecipient(null);
				messageRepository.save(receivedMessage);
			}
			
			List<Message> messagesSent = donor.getMessagesSent();
			for (Iterator<Message> iterator = messagesSent.iterator(); iterator.hasNext();) {
				Message sentMessage = (Message) iterator.next();
				sentMessage.setSender(null);;
				messageRepository.save(sentMessage);
			}
			
			donorRepository.deleteById(donorId);
		}
	}
	
//	Not needed for REST API:
	public void deleteAllDonors() {
		List<Donor> donors = (List<Donor>) donorRepository.findAll();
		for (Iterator<Donor> iterator = donors.iterator(); iterator.hasNext();) {
			Donor donor = (Donor) iterator.next();
			deleteDonorById(donor.getId());
		}
	}
	
	
	
	public void test() {
//		Delete all donors:
//		deleteAllDonors();
		
		Donor d1 = new Donor();
		d1.setContributedProjects(7);
		d1.setDob(Date.valueOf("1993-06-10"));
		d1.setFirstName("donor1");
		Donor d2 = createDonor(d1);
		
		d1.setLastName("donor_lname");
		updateDonor(d2.getId(), d1);
		

	}
}
