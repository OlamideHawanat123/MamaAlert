package com.mamaalert.services;

import com.mamaalert.dtos.requests.RegisterPatientRequest;
import com.mamaalert.dtos.responses.RegisterPatientResponse;

public interface HospitalService {
    RegisterPatientResponse registerPatient(RegisterPatientRequest request);
}
