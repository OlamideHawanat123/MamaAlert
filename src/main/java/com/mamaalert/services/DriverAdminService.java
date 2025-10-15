package com.mamaalert.services;

import com.mamaalert.dtos.requests.RegisterDriverRequest;
import com.mamaalert.dtos.responses.RegisterDriverResponse;

public interface DriverAdminService {
    RegisterDriverResponse registerDriver(RegisterDriverRequest request);
}
