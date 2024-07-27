package com.example.projektstartowy.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
// Model Author zawiera klucz obcy do tabeli Book
@Entity
public class AuthorModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthdate")
    private java.util.Date birthdate;
    public AuthorModel(){

    }
    public AuthorModel(Long id, String name, Date birthdate, List<BookModel> books) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.books = books;
    }

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<BookModel> books = new ArrayList<>();
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
    public java.util.Date getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(java.util.Date birthdate) {
        this.birthdate = birthdate;
    }
    public List<BookModel> getBooks() {
        return books;
    }
    public void setBooks(List<BookModel> books) {
        this.books = books;
    }
}
