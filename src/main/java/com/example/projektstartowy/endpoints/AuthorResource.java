package com.example.projektstartowy.endpoints;


import com.example.projektstartowy.model.AuthorModel;
import com.example.projektstartowy.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorResource {
    private final AuthorService authorService;

    public AuthorResource(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AuthorModel>> getAllAuthors() {
        List<AuthorModel> authors = authorService.getAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AuthorModel> getAuthorById(@PathVariable("id") long id) {
        AuthorModel author = authorService.getAuthorById(id);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<AuthorModel> addAuthor(@RequestBody AuthorModel author) {
        AuthorModel newAuthor = authorService.addAuthor(author);
        return new ResponseEntity<>(newAuthor, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<AuthorModel> updateAuthor(@RequestBody AuthorModel author) {
        AuthorModel updatedAuthor = authorService.updateAuthor(author);
        return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable("id") long id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
