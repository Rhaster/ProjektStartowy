package com.example.projektstartowy.service;


import com.example.projektstartowy.DTO.CustomerDTO;
import com.example.projektstartowy.model.UserModel;
import com.example.projektstartowy.repo.UserRepo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final UserRepo userRepository;

    public CustomerService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public List<CustomerDTO> getAllCustomers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CustomerDTO convertToDTO(UserModel userModel) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(userModel.getId());
        dto.setName(userModel.getUsername());
        return dto;
    }
}