package com.backend_project.service;

import com.backend_project.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public User getUserById(int id);
    public User updateUser(User user, int id);
    public List<User> getAllUSer();
}
