package com.backend_project.service;

import com.backend_project.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {
    public Message sendMessage(Message message);
    public List<Message> getAllMessage();
}
