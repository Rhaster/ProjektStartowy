package com.example.projektstartowy.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
// Model Ksiazki, zawiera klucz obcy do tabeli autor
@Entity
public class BookModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "publication_date")
    @Temporal(TemporalType.DATE)
    private Date publicationDate;

    @ManyToOne // Wielu do jednego bo jeden autor moze napisac wiele ksiazek
    @JoinColumn(name = "author_id", nullable = false)
    private AuthorModel author;

    // Gety i Sety dla ksiazki
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Date getPublicationDate() {
        return publicationDate;
    }
    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
    public AuthorModel getAuthor() {
        return author;
    }
    public void setAuthor(AuthorModel author) {
        this.author = author;
    }
}
