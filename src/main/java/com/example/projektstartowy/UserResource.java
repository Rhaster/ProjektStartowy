package com.example.projektstartowy;

import com.example.projektstartowy.model.User;
import com.example.projektstartowy.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// Kontroler api
@RestController
@RequestMapping("/user") // Mapowanie po /user
public class UserResource {
    private final UserService userService;  // zmienna do przechowania instancji klasy

    public UserResource(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/all")  // Wyswietlenie wszystkich uzytkowników
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/find/{id}") // wyswietlenie wskazanego uzytkownika po ID
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        User users = userService.getUserById(id);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PostMapping("/add") // dodanie uzytkownika
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED); // zwrócenie http status CREATED
    }
    @PutMapping("/update") // Update uzytkownika
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User newUser = userService.updateUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK); // zwrócenie http status CREATED
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK); // zwrócenie http status OK po usunieciu
    }
}
