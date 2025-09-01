package com.example.examfinancialsystem.repository;

import com.example.examfinancialsystem.entity.LikeList;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LikeListRepository extends JpaRepository<LikeList, Long> {
    List<LikeList> findByUserId(String userId);
}