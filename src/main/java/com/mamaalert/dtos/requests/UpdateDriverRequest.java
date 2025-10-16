package com.mamaalert.dtos.requests;

import lombok.Data;

@Data
public class UpdateDriverRequest {
    private double longitude;
    private double latitude;
}
