package com.example.examfinancialsystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdatePreferenceRequest {

    @NotNull(message = "購買數量不可為空")
    @Min(value = 1, message = "購買數量至少為1")
    private Integer quantity;

    @NotBlank(message = "扣款帳號不可為空")
    private String purchaseAccount;
}