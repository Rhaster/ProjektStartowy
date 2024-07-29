package com.example.projektstartowy.endpoints;

import com.example.projektstartowy.DTO.OrderDTO;
import com.example.projektstartowy.model.OrderModel;
import com.example.projektstartowy.model.UserModel;
import com.example.projektstartowy.repo.UserRepo;
import com.example.projektstartowy.service.OrderService;
import com.example.projektstartowy.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserAddons {
    private final UserService userService;  // zmienna do przechowania instancji klasy
    private final OrderService orderService;
    private final UserRepo userRepo;
    public UserAddons(UserService userService, OrderService orderService,UserRepo userRepo) {
        this.userService = userService;
        this.orderService = orderService;
        this.userRepo = userRepo;
    }

    @RequestMapping("/api/user")
    public ResponseEntity<Optional<UserModel>> user() {
        Optional<UserModel> user = userService.getLoggedInUser();
        return ResponseEntity.ok(user);
    }
    @RequestMapping("/orders/all")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {

        List<OrderDTO> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @PostMapping("user/orders/add")
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
    @GetMapping("user/orders/find/{id}")
    public ResponseEntity<OrderModel> getOrderById(@PathVariable Long id) {
        Optional<OrderModel> order = Optional.ofNullable(orderService.getOrderById(id));
        return order.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("user/orders/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        boolean isDeleted = orderService.isOrderDeleted(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @PutMapping("user/orders/update")
    public ResponseEntity<OrderModel> updateOrder(@RequestBody OrderModel order) {
        OrderModel updatedOrder = orderService.updateOrder(order);
        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
