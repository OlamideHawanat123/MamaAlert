package com.mamaalert.services;

import com.mamaalert.data.model.Driver;

public interface DriverService {
    Driver updateLocation(String email, double latitude, double longitude);
}
