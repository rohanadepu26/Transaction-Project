package com.optiminastic.transactionproject.transactionproject.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optiminastic.transactionproject.transactionproject.service.WalletService;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/balance")
    public BigDecimal getBalance(@RequestHeader("client-id") Long clientId) {
        return walletService.getBalance(clientId);
    }
}