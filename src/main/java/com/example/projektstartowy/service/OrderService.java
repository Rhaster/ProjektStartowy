package com.example.projektstartowy.service;

import com.example.projektstartowy.exception.OrderNotFoundException;
import com.example.projektstartowy.model.OrderModel;
import com.example.projektstartowy.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepo orderRepo;
    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }
    public OrderModel addOrder(OrderModel order) {
        return orderRepo.save(order);
    }
    public List<OrderModel> getAllOrders() {
        return orderRepo.findAll();
    }
    public OrderModel updateOrder(OrderModel order) {
        return orderRepo.save(order);
    }
    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }
    public OrderModel getOrderById(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order by id " + id + " was not found"));
    }
}
