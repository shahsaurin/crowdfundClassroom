package com.project.emp_classrooms.services.jaxrs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.emp_classrooms.daos.MessageDao;
import com.project.emp_classrooms.entities.Message;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MessageService {
	
	@Autowired
	MessageDao messageDao;
	
	@PostMapping("/api/sender/{sid}/recipient/{rid}/message")
	public void sendMessage(
			@PathVariable("sid") int senderId,
			@PathVariable("rid") int recipientId,
			@RequestBody Message message) {
		messageDao.sendMessage(senderId, recipientId, message);
	}
	
	@GetMapping("/api/person/{pid}/receivedmessages")
	public List<Message> findReceivedMessagesForPerson (
			@PathVariable("pid") int personId) {
		return messageDao.findReceivedMessagesForPerson(personId);
	}
	
	@GetMapping("/api/person/{pid}/sentmessages")
	public List<Message> findSentMessagesForPerson (
			@PathVariable("pid") int personId) {
		return messageDao.findSentMessagesForPerson(personId);
	} 
	
	@GetMapping("/api/message")
	public List<Message> findAllMessages() {
		return messageDao.findAllMessages();
	}
	
	@DeleteMapping("/api/message/{mid}")
	public void deleteMessageById (
			@PathVariable("mid") int messageId) {
		messageDao.deleteMessageById(messageId);
	}
}
