package com.mamaalert.controller;

import com.mamaalert.dtos.requests.RegisterDriverAdminRequest;
import com.mamaalert.dtos.requests.RegisterHospitalRequest;
import com.mamaalert.dtos.requests.RegisterSuperAdminRequest;
import com.mamaalert.dtos.responses.RegisterDriverAdminResponse;
import com.mamaalert.dtos.responses.RegisterHospitalResponse;
import com.mamaalert.dtos.responses.RegisterSuperAdminResponse;
import com.mamaalert.services.SuperAdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/superAdmin/")
public class SuperAdminController {
    @Autowired
    private SuperAdminService superAdminService;
    @PostMapping("registerSuperAdmin")
    public ResponseEntity<?> registerSuperAdmin(@RequestBody RegisterSuperAdminRequest request){
        RegisterSuperAdminResponse response = superAdminService.registerSuperAdmin(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PostMapping("registerHospital")
    public ResponseEntity<?> registerHospital(@Valid  @RequestBody RegisterHospitalRequest request){
        RegisterHospitalResponse response = superAdminService.registerHospital(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("registerDriverAdmin")
    public ResponseEntity<?> registerDriverAdmin(@Valid @RequestBody RegisterDriverAdminRequest request){
        RegisterDriverAdminResponse response = superAdminService.registerDriverAdmin(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
