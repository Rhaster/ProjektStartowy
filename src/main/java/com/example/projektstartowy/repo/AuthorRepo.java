package com.example.projektstartowy.repo;



import com.example.projektstartowy.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<AuthorModel, Long> {
}

