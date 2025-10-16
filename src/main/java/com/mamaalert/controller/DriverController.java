package com.mamaalert.controller;

import com.mamaalert.data.model.Driver;
import com.mamaalert.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PutMapping("/update-location")
    public ResponseEntity<?> updateLocation(
            Authentication authentication,
            @RequestParam double latitude,
            @RequestParam double longitude
    ) {
        String email = authentication.getName();
        Driver updatedDriver = driverService.updateLocation(email, latitude, longitude);

        return ResponseEntity.ok("Location updated successfully: Lat="
                + updatedDriver.getLatitude() + ", Lon=" + updatedDriver.getLongitude());
    }
}

