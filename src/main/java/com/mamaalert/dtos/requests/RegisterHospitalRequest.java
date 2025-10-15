package com.mamaalert.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterHospitalRequest extends RegisterSuperAdminRequest{
    @NotBlank(message = "Address is required")
    private String address;

}
