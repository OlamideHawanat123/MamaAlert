package com.mamaalert.dtos.requests;

import lombok.Data;

@Data
public class RegisterSuperAdminRequest {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
}
