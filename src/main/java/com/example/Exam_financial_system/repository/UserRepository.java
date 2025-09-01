package com.example.Exam_financial_system.repository;

import com.example.Exam_financial_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}