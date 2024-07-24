package com.example.projektstartowy.service;

import com.example.projektstartowy.exception.UserNotFoundException;
import com.example.projektstartowy.model.User;
import com.example.projektstartowy.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;
    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    public User addUser(User user) {
        return userRepo.save(user);
    }
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    public User updateUser(User user) {
        return userRepo.save(user);
    }
    public void deleteUser(Long id) {
        userRepo.deleteUserById(id);
    }
    public User getUserById(Long id) {
        return userRepo.findUserById(id).orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

}
