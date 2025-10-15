package com.mamaalert.dtos.requests;

import com.mamaalert.data.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterHospitalRequest extends RegisterSuperAdminRequest{
    @NotBlank(message = "Address is required")
    private String address;

}
