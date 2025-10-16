package com.mamaalert.dtos.requests;

import lombok.Data;

@Data
public class CreateEmergencyRequest {
    private double Longitude;
    private double Latitude;
}
