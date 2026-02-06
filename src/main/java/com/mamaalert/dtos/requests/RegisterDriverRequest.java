package com.mamaalert.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public class RegisterDriverRequest extends RegisterDriverAdminRequest{
    @NotBlank(message = "This field is required")
    public String plateNumber;
}
