package com.backend_project.controller;

import com.backend_project.model.AuthenticationResponse;
import com.backend_project.model.Product;
import com.backend_project.model.User;
import com.backend_project.service.AuthenticationService;
import com.backend_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class UserController {
    private final AuthenticationService authenticationService;

    @Autowired
    private final UserService userService;

    public UserController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request){
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PostMapping("/user/update/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable int id){
        return new ResponseEntity<User>(userService.updateUser(user, id), HttpStatus.OK);
    }

    @GetMapping("user/getAll/**")
    public ResponseEntity<?> getAllUSer(){
        return new ResponseEntity<List<User>>(userService.getAllUSer(), HttpStatus.OK);
    }
}
