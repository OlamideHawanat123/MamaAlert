package com.mamaalert.data.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Drivers")
public class Driver extends DriverAdmin{
}
