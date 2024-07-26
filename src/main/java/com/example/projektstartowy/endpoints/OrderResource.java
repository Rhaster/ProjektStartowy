package com.example.projektstartowy.endpoints;



import com.example.projektstartowy.model.OrderModel;
import com.example.projektstartowy.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderResource {
    private final OrderService orderService;

    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add")
    public ResponseEntity<OrderModel> addBook(@RequestBody OrderModel order) {
        OrderModel newBook = orderService.addOrder(order);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }
}
