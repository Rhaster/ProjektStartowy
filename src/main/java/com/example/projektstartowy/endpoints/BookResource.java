package com.example.projektstartowy.endpoints;

import com.example.projektstartowy.DTO.AuthorDTO;
import com.example.projektstartowy.DTO.BookDTO;
import com.example.projektstartowy.exception.BookNotFoundException;
import com.example.projektstartowy.model.AuthorModel;
import com.example.projektstartowy.model.BookModel;
import com.example.projektstartowy.model.UserModel;
import com.example.projektstartowy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// klasa kontrolujaca endpointy modelu book
@RestController
@RequestMapping("/admin/book")
public class BookResource {
    private final BookService bookService;

    @Autowired
    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {
        BookModel book = new BookModel();
        book.setTitle(bookDTO.getTitle());
        book.setPublicationDate(bookDTO.getPublicationDate());
        if (bookDTO.getAuthor() != null) {
            AuthorModel author = new AuthorModel();
            author.setId(bookDTO.getAuthor().getId()); // Jeżeli masz odpowiednie metody w repozytorium
            book.setAuthor(author);
        }
        BookModel newBook = bookService.addBook(book);
        return new ResponseEntity<>(convertToDTO(newBook), HttpStatus.CREATED);
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

    @GetMapping("/find/{id}")
    public ResponseEntity<BookModel> getBookById(@PathVariable("id") Long id) {
        BookModel book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }



    @PutMapping("/update")
    public ResponseEntity<BookModel> updateBook(@RequestBody BookModel book) {
        BookModel updatedBook = bookService.updateBook(book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
        try {
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
