package com.aditya.Order.controller;

import com.aditya.Order.entity.OrderProduct;
import com.aditya.Order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RequestMapping("api/order")
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping
    public ResponseEntity<List<OrderProduct>> getAllOrders()
    {
        var data=orderService.getAllOrders();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderProduct> getOrderById(@PathVariable("id") Long id)
    {
        var data=orderService.getOrderById(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<OrderProduct> createProductInventory(@RequestBody OrderProduct order) throws URISyntaxException {
        var data=orderService.createOrder(order);
        return new ResponseEntity<>(data,HttpStatus.CREATED);
    }
}
