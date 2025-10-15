package com.mamaalert.services;

import com.mamaalert.data.model.User;
import com.mamaalert.dtos.requests.LoginRequest;
import com.mamaalert.dtos.responses.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    User findUserByEmail(String email);
}
