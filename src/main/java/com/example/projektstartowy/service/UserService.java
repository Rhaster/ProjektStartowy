package com.example.projektstartowy.service;

import com.example.projektstartowy.exception.UserNotFoundException;
import com.example.projektstartowy.model.UserModel;

import com.example.projektstartowy.repo.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;


    public Optional<UserModel> getLoggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        return userRepo.findUserByUsername(username);
    }
    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    public UserModel addUser(UserModel user) {
        return userRepo.save(user);
    }
    public List<UserModel> getAllUsers() {
        return userRepo.findAll();
    }
    public UserModel updateUser(UserModel user) {
        return userRepo.save(user);
    }
    public void deleteUser(Long id) {
        userRepo.deleteUserById(id);
    }
    public UserModel getUserById(Long id) {
        return userRepo.findUserById(id).orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> user = userRepo.findUserByUsername(username);
        if (user.isPresent()) { // jest uzytkownik
            var userObj = user.get();
            return  User.builder().username(userObj.getUsername()).
                    password(userObj.getPassword()).roles(userObj.getRole()).build();
        }else {
            throw new UsernameNotFoundException("Username " + username + " was not found"); // wyjatek brak uzytkownika
        }

    }
    private String[] GetRoles(UserModel user){
        if(user.getRole() == null){
            return new String[]{"USER"};
        }
        return user.getRole().split(",");
    }
}
