package com.mamaalert.controller;

import com.mamaalert.dtos.requests.RegisterDriverRequest;
import com.mamaalert.dtos.responses.RegisterDriverResponse;
import com.mamaalert.services.DriverAdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/DriverAdmin/")
public class DriverAdminController {
    @Autowired
    private DriverAdminService driverAdminService;

    @PostMapping("registerDriver")
    public ResponseEntity<?> registerDriver(@Valid @RequestBody RegisterDriverRequest request){
        RegisterDriverResponse response = driverAdminService.registerDriver(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
