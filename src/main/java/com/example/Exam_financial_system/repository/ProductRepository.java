package com.example.Exam_financial_system.repository;

import com.example.Exam_financial_system.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}