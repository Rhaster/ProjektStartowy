package com.example.projektstartowy.service;
import com.example.projektstartowy.exception.BookNotFoundException;
import com.example.projektstartowy.model.BookModel;
import com.example.projektstartowy.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepo bookRepo;

    @Autowired
    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public BookModel addBook(BookModel book) {
        return bookRepo.save(book);
    }

    public List<BookModel> getAllBooks() {
        return bookRepo.findAll();
    }

    public BookModel updateBook(BookModel book) {
        return bookRepo.save(book);
    }

    public void deleteBook(Long id) {
        bookRepo.deleteById(id);
    }

    public BookModel getBookById(Long id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book by id " + id + " was not found"));
    }
}
