package com.example.projektstartowy.endpoints;

import com.example.projektstartowy.model.BookModel;
import com.example.projektstartowy.model.UserModel;
import com.example.projektstartowy.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// klasa kontrolujaca endpointy modelu book
@RestController
@RequestMapping("/book")
public class BookResource {
    private final BookService bookService;

    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add") // dodanie ksiazki
    public ResponseEntity<BookModel> addBook(@RequestBody BookModel book) {
        BookModel newBook = bookService.addBook(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }
    @GetMapping("/all")  // Wyswietlenie wszystkich ksiazek
    public ResponseEntity<List<BookModel>> getAllBooks() {
        List<BookModel> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
