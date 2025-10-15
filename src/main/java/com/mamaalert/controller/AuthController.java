package com.mamaalert.controller;

import com.mamaalert.dtos.requests.*;
import com.mamaalert.dtos.responses.LoginResponse;
import com.mamaalert.services.AuthService;
import com.mamaalert.services.DriverAdminService;
import com.mamaalert.services.HospitalService;
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
@RequestMapping("/auth")
public class AuthController {

    private final SuperAdminService superAdminService;
    private final HospitalService hospitalService;
    private final DriverAdminService driverAdminService;
    private final AuthService authService;


    public AuthController(
            SuperAdminService superAdminService,
            HospitalService hospitalService,
            DriverAdminService driverAdminService,
            AuthService authService
    ) {
        this.superAdminService = superAdminService;
        this.hospitalService = hospitalService;
        this.driverAdminService = driverAdminService;
        this.authService = authService;
    }

    @PostMapping("/register/superadmin")
    public ResponseEntity<?> registerSuperAdmin(@RequestBody RegisterSuperAdminRequest request) {
        superAdminService.registerSuperAdmin(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("SuperAdmin registered");
    }

    @PostMapping("/register/hospital")
    public ResponseEntity<?> registerHospital(@RequestBody RegisterHospitalRequest request) {
        superAdminService.registerHospital(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Hospital registered");
    }

    @PostMapping("/register/patient")
    public ResponseEntity<?> registerPatient(@RequestBody RegisterPatientRequest request) {
        hospitalService.registerPatient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Patient registered");
    }

    @PostMapping("/register/driveradmin")
    public ResponseEntity<?> registerDriverAdmin(@RequestBody RegisterDriverAdminRequest request) {
        superAdminService.registerDriverAdmin(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("DriverAdmin registered");
    }

    @PostMapping("/register/driver")
    public ResponseEntity<?> registerDriver(@RequestBody RegisterDriverRequest request) {
        driverAdminService.registerDriver(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Driver registered");
    }

    // login endpoint (single, shared for all roles)
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
