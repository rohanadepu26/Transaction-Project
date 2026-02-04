package com.optiminastic.transactionproject.transactionproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.optiminastic.transactionproject.transactionproject.entites.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByIdAndClientId(Long id, Long clientId);
}
