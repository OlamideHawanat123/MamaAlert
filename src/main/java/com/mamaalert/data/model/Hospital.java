package com.mamaalert.data.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "Hospitals")
public class Hospital extends User{
    private String address;

}
