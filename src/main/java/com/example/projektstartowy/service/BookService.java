package com.example.projektstartowy.service;
import com.example.projektstartowy.DTO.AuthorDTO;
import com.example.projektstartowy.DTO.BookDTO;
import com.example.projektstartowy.exception.BookNotFoundException;
import com.example.projektstartowy.model.BookModel;
import com.example.projektstartowy.repo.BookRepo;
import com.example.projektstartowy.repo.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepo bookRepo;

    @Autowired
    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Transactional
    public BookModel addBook(BookModel book) {
        return bookRepo.save(book);
    }

    @Autowired
    private AuthorRepo authorRepo; // Jeśli potrzebne

    public List<BookDTO> getAllBooks() {
        List<BookModel> books = bookRepo.findAll();
        return books.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private BookDTO convertToDTO(BookModel book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setPublicationDate(book.getPublicationDate());

        if (book.getAuthor() != null) {
            AuthorDTO authorDTO = new AuthorDTO();
            authorDTO.setId(book.getAuthor().getId());
            authorDTO.setName(book.getAuthor().getName());
            dto.setAuthor(authorDTO);
        }
        return dto;
    }
    @Transactional
    public BookModel updateBook(BookModel book) {
        return bookRepo.save(book);
    }

    @Transactional
    public void deleteBook(Long id) {
        if (bookRepo.existsById(id)) {
            bookRepo.deleteById(id);
        } else {
            throw new BookNotFoundException("Book by id " + id + " was not found");
        }
    }

    public BookModel getBookById(Long id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book by id " + id + " was not found"));
    }
}
