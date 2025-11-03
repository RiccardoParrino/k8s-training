package com.parrino.riccardo.sales.rest;

import org.springframework.web.bind.annotation.RestController;

import com.parrino.riccardo.model.SalesRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; 

@RestController
public class SalesRestController {
    
    @PostMapping("/make/request")
    public void create(@RequestBody SalesRequest salesRequest) {
        return;
    }
    

}
