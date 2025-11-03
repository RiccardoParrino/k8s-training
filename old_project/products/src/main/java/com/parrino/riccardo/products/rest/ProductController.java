package com.parrino.riccardo.products.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.parrino.riccardo.products.model.Product;
import com.parrino.riccardo.products.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping("path")
    public List<Product> findAll() {
        return productService.findAll();
    }

}
