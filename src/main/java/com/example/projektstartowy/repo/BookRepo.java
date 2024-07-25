package com.example.projektstartowy.repo;

import com.example.projektstartowy.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<BookModel, Long> {
}
