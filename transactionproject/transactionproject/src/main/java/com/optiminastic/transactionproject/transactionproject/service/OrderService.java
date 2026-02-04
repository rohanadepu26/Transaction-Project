package com.optiminastic.transactionproject.transactionproject.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optiminastic.transactionproject.transactionproject.entites.Order;
import com.optiminastic.transactionproject.transactionproject.entites.Wallet;
import com.optiminastic.transactionproject.transactionproject.repository.OrderRepository;
import com.optiminastic.transactionproject.transactionproject.repository.WalletRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

	@Autowired
    private WalletRepository walletRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private FulfillmentClient fulfillmentClient;

    @Transactional
    public Order createOrder(Long clientId, BigDecimal balanceAmount) {

        Wallet wallet = walletRepo.findByClientId(clientId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        if (wallet.getBalanceAmount().compareTo(balanceAmount) < 0) {
            throw new RuntimeException("Insufficient wallet balance");
        }

        // Deduct wallet
        wallet.setBalanceAmount(wallet.getBalanceAmount().subtract(balanceAmount));

        // Create order
        Order order = new Order();
        order.setClientId(clientId);
        order.setBalanceAmount(balanceAmount);
        order.setStatus("CREATED");
        order.setCreatedAt(LocalDateTime.now());
        orderRepo.save(order);

        // Call fulfillment
        String fulfillmentId = fulfillmentClient.createFulfillment(clientId, order.getId());

        order.setFulfillmentId(fulfillmentId);
        order.setStatus("FULFILLED");

        return order;
    }

    public Order getOrder(Long orderId, Long clientId) {
        return orderRepo.findByIdAndClientId(orderId, clientId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
