package com.example.examfinancialsystem.service;

import com.example.examfinancialsystem.dto.AddPreferenceRequest;
import com.example.examfinancialsystem.dto.UpdatePreferenceRequest;
import com.example.examfinancialsystem.dto.UserPreferenceResponse;

public interface PreferenceService {
    void addPreference(AddPreferenceRequest request);
    UserPreferenceResponse getUserPreferences(String userId);

    void updatePreference(Long sn, UpdatePreferenceRequest request);
    void deletePreference(Long sn);
}