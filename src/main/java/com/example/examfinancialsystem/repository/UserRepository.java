package com.example.examfinancialsystem.repository;

import com.example.examfinancialsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}