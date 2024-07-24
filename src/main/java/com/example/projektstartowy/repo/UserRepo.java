package com.example.projektstartowy.repo;

import com.example.projektstartowy.model.UserModel;
import com.example.projektstartowy.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserModel, Long> {


    void deleteUserById(Long id); // usun uzytkownika po ID

    Optional<UserModel> findUserById(Long id); // Znajdz uzytkownika po ID, opcjonalne bo moze nie znalezc
    Optional<UserModel> findUserByEmail(String email);// Znajdz uzytkownika po Email, opcjonalne bo moze nie znalezc
    Optional<UserModel> findUserByUsername(String username);// Znajdz uzytkownika po Nazwie, opcjonalne bo moze nie znalezc
}
