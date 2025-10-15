package com.mamaalert.data.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "Patients")
public class Patient extends User{
    private String hospitalId;
    private List<String> relativeNumbers;

}
