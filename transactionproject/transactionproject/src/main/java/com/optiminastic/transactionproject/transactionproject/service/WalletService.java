package com.optiminastic.transactionproject.transactionproject.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optiminastic.transactionproject.transactionproject.entites.LedgerType;
import com.optiminastic.transactionproject.transactionproject.entites.Wallet;
import com.optiminastic.transactionproject.transactionproject.entites.WalletLedger;
import com.optiminastic.transactionproject.transactionproject.repository.WalletLedgerRepository;
import com.optiminastic.transactionproject.transactionproject.repository.WalletRepository;

import jakarta.transaction.Transactional;

@Service
public class WalletService {

	@Autowired
    private WalletRepository walletRepo;

    @Autowired
    private WalletLedgerRepository ledgerRepo;

    @Transactional
    public void credit(Long clientId, BigDecimal balanceAmount) {
        Wallet wallet = walletRepo.findByClientId(clientId)
                .orElseGet(() -> {
                    Wallet w = new Wallet();
                    w.setClientId(clientId);
                    w.setBalanceAmount(BigDecimal.ZERO);
                    return w;
                });

        wallet.setBalanceAmount(wallet.getBalanceAmount().add(balanceAmount));
        walletRepo.save(wallet);

        saveLedger(clientId, balanceAmount, LedgerType.CREDIT);
    }

    @Transactional
    public void debit(Long clientId, BigDecimal balanceAmount) {
        Wallet wallet = walletRepo.findByClientId(clientId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        if (wallet.getBalanceAmount().compareTo(balanceAmount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        wallet.setBalanceAmount(wallet.getBalanceAmount().subtract(balanceAmount));
        saveLedger(clientId, balanceAmount, LedgerType.DEBIT);
    }

    public BigDecimal getBalance(Long clientId) {
        return walletRepo.findByClientId(clientId)
                .map(Wallet::getBalanceAmount)
                .orElse(BigDecimal.ZERO);
    }

    private void saveLedger(Long clientId, BigDecimal balanceAmount, LedgerType type) {
        WalletLedger ledger = new WalletLedger();
        ledger.setClientId(clientId);
        ledger.setBalanceAmount(balanceAmount);
        ledger.setType(type);
        ledger.setCreatedAt(LocalDateTime.now());
        ledgerRepo.save(ledger);
    }
}

