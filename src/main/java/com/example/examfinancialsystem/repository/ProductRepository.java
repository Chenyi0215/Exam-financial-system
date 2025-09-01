package com.example.examfinancialsystem.repository;

import com.example.examfinancialsystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}