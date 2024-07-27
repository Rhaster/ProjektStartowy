package com.example.projektstartowy.repo;

import com.example.projektstartowy.model.BookModel;
import com.example.projektstartowy.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepo extends JpaRepository<BookModel, Long> {

    void deleteBookModelById(Long id); // usun uzytkownika po ID

    Optional<BookModel> findBookModelById(Long id); // Znajdz uzytkownika po ID, opcjonalne bo moze nie znalezc
}
