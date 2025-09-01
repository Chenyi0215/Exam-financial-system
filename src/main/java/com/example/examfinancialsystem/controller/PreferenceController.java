package com.example.examfinancialsystem.controller;

import com.example.examfinancialsystem.dto.AddPreferenceRequest;
import com.example.examfinancialsystem.dto.UserPreferenceResponse;
import com.example.examfinancialsystem.service.PreferenceService;
import com.example.examfinancialsystem.dto.UpdatePreferenceRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PreferenceController {

    @Autowired
    private PreferenceService preferenceService;

    @PostMapping("/preferences")
    public ResponseEntity<Map<String, String>> addPreference(@Valid @RequestBody AddPreferenceRequest request) {
        preferenceService.addPreference(request);
        Map<String, String> response = Collections.singletonMap("message", "新增成功");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}/preferences")
    public ResponseEntity<UserPreferenceResponse> getUserPreferences(@PathVariable String userId) {
        UserPreferenceResponse response = preferenceService.getUserPreferences(userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/preferences/{sn}")
    public ResponseEntity<Map<String, String>> updatePreference(
            @PathVariable Long sn,
            @Valid @RequestBody UpdatePreferenceRequest request) {
        preferenceService.updatePreference(sn, request);
        Map<String, String> response = Collections.singletonMap("message", "更新成功");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/preferences/{sn}")
    public ResponseEntity<Void> deletePreference(@PathVariable Long sn) {
        preferenceService.deletePreference(sn);
        return ResponseEntity.noContent().build();
    }
}