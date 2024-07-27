package com.example.projektstartowy.repo;

import com.example.projektstartowy.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepo extends JpaRepository<OrderModel, Long> {
    void deleteOrderByorderID(Long orderID); // usun zamownienie po ID

    Optional<OrderModel> findOrderModelByorderID(Long orderID); // Znajdz zamowienie po ID, opcjonalne bo moze nie znalezc
    Optional<OrderModel> findOrderModelBycustomerId(Long id);// Znajdz zamowienie po id uzytkownika, opcjonalne bo moze nie znalezc

}
