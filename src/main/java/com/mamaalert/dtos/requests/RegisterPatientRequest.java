package com.mamaalert.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegisterPatientRequest extends RegisterSuperAdminRequest{
    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "HospitalId is required")
    private String hospitalId;

    @NotEmpty(message = "At least one relative number is required")
    private List< @NotBlank(message = "Relative number cannot be blank") String> relativeNumber;

}
