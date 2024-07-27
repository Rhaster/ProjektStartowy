package com.example.projektstartowy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class UserModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id; // Klucz główny

    private String username;
    private String email;
    private String password;
    private String role; // ADMIN lub USER
    // Konstruktor
    public UserModel() {}

    public UserModel( String username, String email, String password,String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    // Gety i setty
    public Long getId() {
        return id;
    }
    public String getRole(){
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String name) {
        this.username = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username +
                ", email=" + email + ", password=" + password + "]";
    }
}