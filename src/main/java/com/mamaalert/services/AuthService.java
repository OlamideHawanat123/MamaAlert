package com.mamaalert.services;

import com.mamaalert.dtos.requests.LoginRequest;
import com.mamaalert.dtos.responses.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
