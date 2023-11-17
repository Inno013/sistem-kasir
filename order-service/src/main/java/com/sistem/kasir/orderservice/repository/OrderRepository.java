package com.sistem.kasir.orderservice.repository;

import com.sistem.kasir.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
