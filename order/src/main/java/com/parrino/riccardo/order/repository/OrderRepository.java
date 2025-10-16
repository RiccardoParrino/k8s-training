package com.parrino.riccardo.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parrino.riccardo.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
}
