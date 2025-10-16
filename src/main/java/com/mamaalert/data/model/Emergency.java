package com.mamaalert.data.model;

import com.mamaalert.data.model.EmergencyStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "emergencies")
public class Emergency {

    @Id
    private String id;

    @DBRef
    private Patient patient;

    private double latitude;
    private double longitude;

    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;

    private EmergencyStatus status;

    @DBRef
    private Driver resolvedBy;
}

