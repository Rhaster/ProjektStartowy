package com.example.projektstartowy.repo;

import com.example.projektstartowy.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderModel, Long> {
}
