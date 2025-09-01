package com.example.examfinancialsystem.dto;

import lombok.Data;
import java.util.List;

@Data
public class UserPreferenceResponse {
    private String userEmail;
    private List<PreferenceDTO> preferences;
}