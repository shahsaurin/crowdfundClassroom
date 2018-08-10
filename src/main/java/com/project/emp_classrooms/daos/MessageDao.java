package com.project.emp_classrooms.daos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.sound.midi.Receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.emp_classrooms.entities.Donor;
import com.project.emp_classrooms.entities.Message;
import com.project.emp_classrooms.entities.Person;
import com.project.emp_classrooms.enums.User;
import com.project.emp_classrooms.repositories.MessageRepository;
import com.project.emp_classrooms.repositories.PersonRepository;

@Component
public class MessageDao {

	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	PersonRepository personRepository;
	
	
	public void sendMessage(int senderId, int recipientId, Message message) {
		Person sender = personRepository.findById(senderId).get();
		Person recipient = personRepository.findById(recipientId).get();
		
		message.setSender(sender);
		message.setRecipient(recipient);
		
		if(sender instanceof Donor || recipient instanceof Donor) {
			message.setMessageCategory(User.Donor);
		} else {
			message.setMessageCategory(User.Teacher);
		}
		
		sender.getMessagesSent().add(message);
		recipient.getMessagesReceived().add(message);
		
		messageRepository.save(message);
		personRepository.save(sender);
		personRepository.save(recipient);
	}
	
	
	public List<Message> findReceivedMessagesForPerson(int personId) {		
		List<Message> allMessageInDb = (List<Message>) messageRepository.findAll();
		List<Message> receivedMessages = new ArrayList<Message>();
		
		for (Iterator<Message> iterator = allMessageInDb.iterator(); iterator.hasNext();) {
			Message message = (Message) iterator.next();
			System.out.println(message);
			if(message.getRecipient().getId() == personId) {
				receivedMessages.add(message);
			}
		}
		return receivedMessages;
	}
	
	
	public List<Message> findSentMessagesForPerson(int personId) {
		List<Message> allMessageInDb = (List<Message>) messageRepository.findAll();
		List<Message> sentMessages = new ArrayList<Message>();
		
		for (Iterator<Message> iterator = allMessageInDb.iterator(); iterator.hasNext();) {
			Message message = (Message) iterator.next();
			if(message.getSender().getId() == personId) {
				sentMessages.add(message);
			}
		}
		return sentMessages;
	}
	
	
//	'createMessage' is not used for the app:
	public Message createMessage(Message message) {
		return messageRepository.save(message);
	}
	
	public List<Message> findAllMessages() {
		return (List<Message>) messageRepository.findAll();
	}
	
	public Message findMessageById(int messageId) {
		Optional<Message> optMessage = messageRepository.findById(messageId);
		if(optMessage.isPresent()) {
			return optMessage.get();
		}
		return null;
	}
	
	public Message updateMessage(int id, Message updatedMessage) {
		Optional<Message> optMessage = messageRepository.findById(id);
		if(optMessage.isPresent()) {
			Message oldMessage = optMessage.get();
			oldMessage.setDescription(updatedMessage.getDescription());
			oldMessage.setLastUpdated(new Date(Calendar.getInstance().getTimeInMillis()));
			return messageRepository.save(oldMessage);
		}
		return null;
	}
	
	void deleteMessageById(int id) {
		messageRepository.deleteById(id);
	}
	
	void deleteAllMessages() {
		messageRepository.deleteAll();
	}
	
	
	
	public void test() {
//		Delete all messages:
//		deleteAllMessages();
//		
//		Message d1 = new Message();
//		d1.setDescription("Sample_message 1");
//		d1.setMessageCategory(User.Donor);
//		
//		Message d2 = createMessage(d1);
//		
//		d1.setDescription("edited_description 1");
//		updateMessage(d2.getId(), d1);
		
		
	}
}
