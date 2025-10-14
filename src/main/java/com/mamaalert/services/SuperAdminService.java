package com.mamaalert.services;

import com.mamaalert.dtos.requests.RegisterSuperAdminRequest;
import com.mamaalert.dtos.responses.RegisterSuperAdminResponse;
import org.springframework.stereotype.Service;


public interface SuperAdminService {
    RegisterSuperAdminResponse registerSuperAdmin(RegisterSuperAdminRequest request);
}
