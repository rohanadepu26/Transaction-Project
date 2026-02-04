package com.optiminastic.transactionproject.transactionproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optiminastic.transactionproject.transactionproject.entites.Order;
import com.optiminastic.transactionproject.transactionproject.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(
            @RequestHeader("client-id") Long clientId,
            @RequestBody OrderRequest req) {

        return orderService.createOrder(clientId, req.getBalanceAmount());
    }

    @GetMapping("/{orderId}")
    public Order getOrder(
            @RequestHeader("client-id") Long clientId,
            @PathVariable Long orderId) {

        return orderService.getOrder(orderId, clientId);
    }
    
}
