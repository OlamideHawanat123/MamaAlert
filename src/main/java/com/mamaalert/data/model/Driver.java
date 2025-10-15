package com.mamaalert.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Drivers")
public class Driver extends User{
    private String branchLocation;
    private String address;
}
