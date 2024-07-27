package com.example.projektstartowy.service;

import com.example.projektstartowy.DTO.CustomerDTO;
import com.example.projektstartowy.exception.OrderNotFoundException;
import com.example.projektstartowy.model.OrderModel;
import com.example.projektstartowy.model.UserModel;
import com.example.projektstartowy.repo.OrderRepo;
import com.example.projektstartowy.DTO.OrderDTO;
import com.example.projektstartowy.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepo orderRepo;
    private final UserRepo userRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo, UserRepo userRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
    }

    public OrderModel addOrder(OrderModel order) {
        // Sprawdź, czy klient istnieje
        if (order.getCustomer() == null || order.getCustomer().getId() == null) {
            throw new IllegalArgumentException("Customer ID is required");
        }

        Optional<UserModel> customer = userRepo.findById(order.getCustomer().getId());
        if (customer.isEmpty()) {
            throw new IllegalArgumentException("Customer not found");
        }

        order.setCustomer(customer.get());
        return orderRepo.save(order);
    }


    public List<OrderDTO> getAllOrders() {
        List<OrderModel> orders = orderRepo.findAll();
        List<OrderDTO> orderDTOs = new ArrayList<>();

        for (OrderModel order : orders) {
            OrderDTO dto = new OrderDTO();
            dto.setOrderID(order.getOrderID());
            dto.setName(order.getName());
            dto.setRentDateStart(order.getRentDateStart());
            dto.setRateDateEnd(order.getRateDateEnd());

            CustomerDTO customerDTO = new CustomerDTO();
            if (order.getCustomer() != null) {
                customerDTO.setId(order.getCustomer().getId());
                customerDTO.setName(order.getCustomer().getUsername());
            }
            dto.setCustomer(customerDTO);

            orderDTOs.add(dto);
        }

        return orderDTOs;
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
    public boolean isOrderDeleted(Long id) {
        if(orderRepo.findOrderModelByorderID(id).isPresent()) {
            ;
            return true;
        }
        return false;
    }
}
