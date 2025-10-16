package com.mamaalert.data.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "Emegencies")
@Getter
@Setter
public class Emergency {
    @Id
    private String id;
    private String patientId;
    private double latitude;
    private double longitude;
    private EmergencyStatus status;
    private LocalDateTime timestamp;

}
