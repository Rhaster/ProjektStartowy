package com.example.projektstartowy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;


    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    public enum AccountType {
        Klient, Dostawca
    }

    // Constructors (optional)
    public User() {}

    public User( String name, String email, AccountType accountType, String password) {
        this.name = name;
        this.email = email;
        this.accountType = accountType;
        this.password = password;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name +
                ", email=" + email + ", accountType=" + accountType + ", password=" + password + "]";
    }
}