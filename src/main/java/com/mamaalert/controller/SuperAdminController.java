package com.mamaalert.controller;

import com.mamaalert.dtos.requests.RegisterSuperAdminRequest;
import com.mamaalert.dtos.responses.RegisterSuperAdminResponse;
import com.mamaalert.services.SuperAdminService;
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
}
