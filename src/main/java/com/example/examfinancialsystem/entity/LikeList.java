package com.example.examfinancialsystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "like_list")
public class LikeList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sn")
    private Long sn;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "product_no", nullable = false)
    private Integer productNo;

    @Column(name = "order_name", nullable = false)
    private Integer orderName;

    @Column(name = "account", nullable = false)
    private String account;

    @Column(name = "total_fee", nullable = false)
    private BigDecimal totalFee;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;
}