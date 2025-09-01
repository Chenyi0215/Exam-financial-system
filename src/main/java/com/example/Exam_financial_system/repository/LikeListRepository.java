package com.example.Exam_financial_system.repository;

import com.example.Exam_financial_system.entity.LikeList;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LikeListRepository extends JpaRepository<LikeList, Long> {
    List<LikeList> findByUserId(String userId);
}