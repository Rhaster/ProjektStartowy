package com.example.projektstartowy;

import com.example.projektstartowy.model.UserModel;
import com.example.projektstartowy.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// Kontroler api
@RestController// Mapowanie po /user
public class UserResource {
    private final UserService userService;  // zmienna do przechowania instancji klasy

    public UserResource(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/all")  // Wyswietlenie wszystkich uzytkowników
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/find/{id}") // wyswietlenie wskazanego uzytkownika po ID
    public ResponseEntity<UserModel> getUserById(@PathVariable("id") long id) {
        UserModel users = userService.getUserById(id);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PostMapping("/add") // dodanie uzytkownika
    public ResponseEntity<UserModel> addUser(@RequestBody UserModel user) {
        UserModel newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED); // zwrócenie http status CREATED
    }
    @PutMapping("/update") // Update uzytkownika
    public ResponseEntity<UserModel> updateUser(@RequestBody UserModel user) {
        UserModel newUser = userService.updateUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK); // zwrócenie http status CREATED
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK); // zwrócenie http status OK po usunieciu
    }
    @GetMapping("/login")
    public String handleLogin() {
        return "customloginpage";
    }
    @GetMapping("/logout")
    public String handleLogout() {
        return "customloginpage";
    }
}
