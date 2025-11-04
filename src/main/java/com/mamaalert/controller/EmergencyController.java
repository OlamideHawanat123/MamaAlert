package com.mamaalert.controller;

import com.mamaalert.dtos.requests.CreateEmergencyRequest;
import com.mamaalert.dtos.responses.CreateEmergencyResponse;
import com.mamaalert.services.EmergencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("patient/")
public class EmergencyController {
    @Autowired
    private EmergencyService emergencyService;

    @PostMapping("createEmergency")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?> createEmergency(@RequestBody CreateEmergencyRequest request){
        CreateEmergencyResponse response = emergencyService.createEmergency(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/emergency/{id}/resolve")
    @PreAuthorize("hasRole('DRIVER')")
    public ResponseEntity<?> resolveEmergency(@PathVariable String id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String driverEmail = auth.getName();
        emergencyService.resolveEmergency(id, driverEmail);
        return ResponseEntity.ok("Emergency resolved successfully");
    }

}