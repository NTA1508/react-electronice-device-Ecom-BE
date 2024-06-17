package com.backend_project.controller;

import com.backend_project.model.Message;
import com.backend_project.model.Product;
import com.backend_project.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message")
@CrossOrigin
public class MessageController {

    @Autowired
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }


    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody Message message){
        return new ResponseEntity<Message>(messageService.sendMessage(message), HttpStatus.OK);
    }


    @GetMapping("/allMessage")
    public ResponseEntity<?> getAllMessage(){
        return new ResponseEntity<List<Message>>(messageService.getAllMessage(), HttpStatus.OK);
    }
}
