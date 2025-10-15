package com.mamaalert.data.repository;

import com.mamaalert.data.model.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DriverRepo extends MongoRepository<Driver, String> {
}
