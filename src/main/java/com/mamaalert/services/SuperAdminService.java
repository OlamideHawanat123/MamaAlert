package com.mamaalert.services;

import com.mamaalert.dtos.requests.RegisterDriverAdminRequest;
import com.mamaalert.dtos.requests.RegisterHospitalRequest;
import com.mamaalert.dtos.requests.RegisterSuperAdminRequest;
import com.mamaalert.dtos.responses.RegisterDriverAdminResponse;
import com.mamaalert.dtos.responses.RegisterHospitalResponse;
import com.mamaalert.dtos.responses.RegisterSuperAdminResponse;
import org.springframework.stereotype.Service;


public interface SuperAdminService {
    RegisterSuperAdminResponse registerSuperAdmin(RegisterSuperAdminRequest request);

    RegisterHospitalResponse registerHospital(RegisterHospitalRequest request);

    RegisterDriverAdminResponse registerDriverAdmin(RegisterDriverAdminRequest request);
}
