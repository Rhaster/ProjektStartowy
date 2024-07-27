package com.example.projektstartowy.endpoints;


import com.example.projektstartowy.model.AuthorModel;
import com.example.projektstartowy.model.BookModel;
import com.example.projektstartowy.repo.BookRepo;
import com.example.projektstartowy.repo.AuthorRepo;
import com.example.projektstartowy.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/author")
public class AuthorResource {

        private final AuthorService authorService;
    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
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

        @Autowired
        private BookRepo bookRepository;
        @PostMapping("/add")
        public ResponseEntity<AuthorModel> addAuthor(@RequestBody AuthorModel author) {
            List<BookModel> books = author.getBooks();
            AuthorModel newAuthor = authorService.addAuthor(author, books);
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

