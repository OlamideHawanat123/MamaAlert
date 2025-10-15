package com.mamaalert.controller;

import com.mamaalert.dtos.requests.RegisterPatientRequest;
import com.mamaalert.dtos.responses.RegisterPatientResponse;
import com.mamaalert.services.HospitalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/hospital/")
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @PostMapping("registerPatient")
    public ResponseEntity<?> registerPatient(@Valid @RequestBody RegisterPatientRequest request){
        RegisterPatientResponse response = hospitalService.registerPatient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
