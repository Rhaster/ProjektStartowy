package com.example.projektstartowy.service;

import com.example.projektstartowy.exception.AuthorNotFoundException;
import com.example.projektstartowy.model.AuthorModel;
import com.example.projektstartowy.model.BookModel;
import com.example.projektstartowy.repo.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepo authorRepo;

    @Autowired
    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    public AuthorModel addAuthor(AuthorModel author, List<BookModel> books) {
        if (books != null && !books.isEmpty()) {
            for (BookModel book : books) {
                book.setAuthor(author);  // Ustaw autora dla każdej książki
            }
            author.setBooks(books);  // Ustaw książki dla autora
        }
        return authorRepo.save(author);  // Zapisz autora razem z książkami
    }

    public List<AuthorModel> getAllAuthors() {
        return authorRepo.findAll();
    }

    public AuthorModel updateAuthor(AuthorModel author) {
        return authorRepo.save(author);
    }

    public void deleteAuthor(Long id) {
        authorRepo.deleteById(id);
    }

    public AuthorModel getAuthorById(Long id) {
        return authorRepo.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author by id " + id + " was not found"));
    }
}
