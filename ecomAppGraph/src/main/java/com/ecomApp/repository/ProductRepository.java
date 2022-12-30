package com.ecomApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomApp.entities.Product;

public interface ProductRepository extends JpaRepository<Product, String>{

}
