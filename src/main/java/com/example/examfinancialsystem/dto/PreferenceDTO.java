package com.example.examfinancialsystem.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PreferenceDTO {
    private Long sn;
    private String productName;
    private String account;
    private BigDecimal totalFee;
    private BigDecimal totalAmount;
}