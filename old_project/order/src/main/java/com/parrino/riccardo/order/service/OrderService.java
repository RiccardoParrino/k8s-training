package com.parrino.riccardo.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parrino.riccardo.order.model.Order;
import com.parrino.riccardo.order.repository.OrderRepository;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll () {
        return orderRepository.findAll();
    }

}
