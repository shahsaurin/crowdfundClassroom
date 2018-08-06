package com.project.emp_classrooms.daos;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.rmi.CORBA.UtilDelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.emp_classrooms.entities.Message;
import com.project.emp_classrooms.enums.User;
import com.project.emp_classrooms.repositories.MessageRepository;

@Component
public class MessageDao {

	@Autowired
	MessageRepository messageRepository;
	
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
		deleteAllMessages();
		
		Message d1 = new Message();
		d1.setDescription("Sample_message 1");
		d1.setMessageCategory(User.Donor);
		
		Message d2 = createMessage(d1);
		
		d1.setDescription("edited_description 1");
		updateMessage(d2.getId(), d1);
		
		
	}
}
