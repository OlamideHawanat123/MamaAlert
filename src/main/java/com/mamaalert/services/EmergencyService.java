package com.mamaalert.services;

import com.mamaalert.data.model.Emergency;
import com.mamaalert.dtos.requests.CreateEmergencyRequest;
import com.mamaalert.dtos.responses.CreateEmergencyResponse;

public interface EmergencyService {
    CreateEmergencyResponse createEmergency(CreateEmergencyRequest request);
    void resolveEmergency(String emergencyId, String driverEmail);
}
