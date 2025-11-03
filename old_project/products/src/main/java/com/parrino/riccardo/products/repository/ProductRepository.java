package com.parrino.riccardo.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parrino.riccardo.products.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
