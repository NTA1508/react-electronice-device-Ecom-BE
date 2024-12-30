package com.backend_project.controller;

import com.backend_project.model.Cart;
import com.backend_project.model.Message;
import com.backend_project.model.Product;
import com.backend_project.repository.MessageRepository;
import com.backend_project.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/message")
@CrossOrigin
public class MessageController {

    @Autowired
    private final MessageService messageService;

    @Autowired
    private final MessageRepository messageRepository;

    public MessageController(MessageService messageService, MessageRepository messageRepository) {
        this.messageService = messageService;
        this.messageRepository = messageRepository;
    }


    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody Message message){
        return new ResponseEntity<Message>(messageService.sendMessage(message), HttpStatus.OK);
    }


    @GetMapping("/allMessage")
    public ResponseEntity<?> getAllMessage(){
        return new ResponseEntity<List<Message>>(messageService.getAllMessage(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteMessage(@PathVariable int id) throws IOException{
        Optional<Message> message = messageRepository.findById(id);
        messageService.deleteMessage(id);
        return  "Delete Successsfully";
    }
}
