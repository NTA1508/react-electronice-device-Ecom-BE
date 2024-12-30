package com.backend_project.service;

import com.backend_project.exception.ResourceNotFoundException;
import com.backend_project.model.Message;
import com.backend_project.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImp implements MessageService{

    @Autowired
    private final MessageRepository messageRepository;

    public MessageServiceImp(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }


    @Override
    public Message sendMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getAllMessage() {
        return messageRepository.findAll();
    }

    @Override
    public void deleteMessage(int id) {
        Message message = messageRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(false, "Message not found"));
        messageRepository.delete(message);
    }
}
