package com.optiminastic.transactionproject.transactionproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optiminastic.transactionproject.transactionproject.service.WalletService;

@RestController
@RequestMapping("/admin/wallet")
public class AdminWalletController {

	@Autowired
    private WalletService walletService;

    @PostMapping("/credit")
    public void credit(@RequestBody WalletRequest req) {
        walletService.credit(req.getClientId(), req.getBalanceAmount());
    }

    @PostMapping("/debit")
    public void debit(@RequestBody WalletRequest req) {
        walletService.debit(req.getClientId(), req.getBalanceAmount());
    }
}
