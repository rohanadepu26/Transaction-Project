package com.optiminastic.transactionproject.transactionproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.optiminastic.transactionproject.transactionproject.entites.WalletLedger;

@Repository
public interface WalletLedgerRepository extends JpaRepository<WalletLedger, Long> {
}