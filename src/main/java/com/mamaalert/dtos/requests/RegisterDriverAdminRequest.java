package com.mamaalert.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDriverAdminRequest extends RegisterSuperAdminRequest{
    @NotBlank(message = "Branch is required")
    private String branch;

    @NotBlank(message = "Address cannot be empty")
    private String address;
}
