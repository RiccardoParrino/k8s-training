package com.parrino.riccardo.order.rest;

import org.springframework.web.bind.annotation.RestController;

import com.parrino.riccardo.order.model.Order;
import com.parrino.riccardo.order.service.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @ResponseBody
    @GetMapping("api/order/findAll")
    public List<Order> findAll() {
        return orderService.findAll();
    }

}
