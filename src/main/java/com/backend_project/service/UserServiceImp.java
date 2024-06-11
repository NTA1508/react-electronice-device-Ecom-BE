package com.backend_project.service;

import com.backend_project.exception.ResourceNotFoundException;
import com.backend_project.model.User;
import com.backend_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(false, "User not found"));
    }

    @Override
    public User updateUser(User user, int id) {
        User u = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(false, "User not found"));
        u.setUserName(user.getUserName());
        u.setEmail(user.getEmail());
        u.setPhoneNumber(user.getPhoneNumber());
        u.setAddress(user.getAddress());

        return userRepository.save(u);
    }

    @Override
    public List<User> getAllUSer() {
        return userRepository.findAll();
    }
}
