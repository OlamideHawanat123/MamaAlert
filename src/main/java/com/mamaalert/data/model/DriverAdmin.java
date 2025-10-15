package com.mamaalert.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "DriverAdmin")
public class DriverAdmin extends User{
    private String branchLocation;
    private String address;
}
