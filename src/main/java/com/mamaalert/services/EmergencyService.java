package com.mamaalert.services;

import com.mamaalert.data.model.Emergency;
import com.mamaalert.dtos.requests.CreateEmergencyRequest;

public interface EmergencyService {
    Emergency createEmergency(CreateEmergencyRequest request);
    void resolveEmergency(String id);
}
