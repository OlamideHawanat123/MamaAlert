package com.mamaalert.controller;

import com.mamaalert.dtos.requests.*;
import com.mamaalert.dtos.responses.*;
import com.mamaalert.services.AuthService;
import com.mamaalert.services.DriverAdminService;
import com.mamaalert.services.HospitalService;
import com.mamaalert.services.SuperAdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<?> registerSuperAdmin(@Valid @RequestBody RegisterSuperAdminRequest request) {
        RegisterSuperAdminResponse response = superAdminService.registerSuperAdmin(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/register/hospital")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<?> registerHospital(@Valid @RequestBody RegisterHospitalRequest request) {
        RegisterHospitalResponse response = superAdminService.registerHospital(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/register/patient")
    @PreAuthorize("hasRole('HOSPITAL')")
    public ResponseEntity<?> registerPatient(@Valid@RequestBody RegisterPatientRequest request) {
       RegisterPatientResponse response =  hospitalService.registerPatient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/register/driveradmin")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<?> registerDriverAdmin(@Valid @RequestBody RegisterDriverAdminRequest request) {
        RegisterDriverAdminResponse response = superAdminService.registerDriverAdmin(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/register/d61856.jpgriver")
    @PreAuthorize("hasRole('DRIVER_ADMIN')")
    public ResponseEntity<?> registerDriver(@Valid @RequestBody RegisterDriverRequest request) {
        RegisterDriverResponse response = driverAdminService.registerDriver(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
