package com.example.projektstartowy.endpoints;



import com.example.projektstartowy.DTO.CustomerDTO;
import com.example.projektstartowy.DTO.OrderDTO;
import com.example.projektstartowy.model.OrderModel;
import com.example.projektstartowy.model.UserModel;
import com.example.projektstartowy.repo.OrderRepo;
import com.example.projektstartowy.service.CustomerService;
import com.example.projektstartowy.service.OrderService;
import com.example.projektstartowy.service.UserService;
import com.example.projektstartowy.repo.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/orders")
public class OrderResource {
    private final OrderService orderService;
    private final UserService userService;
    private final CustomerService customerService;
    private final UserRepo userRepo;
    public OrderResource(OrderService orderService, UserService userService, CustomerService customerService, UserRepo userRepo) {
        this.orderService = orderService;
        this.userService = userService;
        this.customerService = customerService;
        this.userRepo = userRepo;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody OrderModel order) {
        try {
            if (order.getCustomer() == null || order.getCustomer().getId() == null) {
                return new ResponseEntity<>("Customer ID is required", HttpStatus.BAD_REQUEST);
            }

            Optional<UserModel> customer = userRepo.findUserById(order.getCustomer().getId());
            if (customer.isEmpty()) {
                return new ResponseEntity<>("Customer not found", HttpStatus.BAD_REQUEST);
            }

            order.setCustomer(customer.get());
            OrderModel newOrder = orderService.addOrder(order);
            return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error saving order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<OrderModel> updateOrder(@RequestBody OrderModel order) {
        OrderModel updatedOrder = orderService.updateOrder(order);
        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        boolean isDeleted = orderService.isOrderDeleted(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<OrderModel> getOrderById(@PathVariable Long id) {
        Optional<OrderModel> order = Optional.ofNullable(orderService.getOrderById(id));
        return order.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/customer/all")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
