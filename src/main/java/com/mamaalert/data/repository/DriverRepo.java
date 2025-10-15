package com.mamaalert.data.repository;

import com.mamaalert.data.model.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DriverRepo extends MongoRepository<Driver, String>, UserRepository<Driver> {
    Optional<Driver> findByEmail(String email);
}
