package com.exxbrain.notification.controller;

import com.exxbrain.notification.ApplicationException;
import com.exxbrain.notification.entity.Order;
import com.exxbrain.notification.entity.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    private final OrderRepository repository;

    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/createOrder/{id}/forUser/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@PathVariable String id, @PathVariable String userId,
                             @RequestBody OrderDetails details) {
        if (repository.existsById(id)) {
            throw new ApplicationException("Order already exists");
        }
        var order = new Order();
        order.setId(id);
        order.setUserId(userId);
        order.setPrice(details.getPrice());
        repository.save(order);
        return order;
    }

}
