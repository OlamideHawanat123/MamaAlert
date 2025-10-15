package com.mamaalert.data.repository;

import com.mamaalert.data.model.Driver;
import com.mamaalert.data.model.DriverAdmin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DriverAdminRepo extends MongoRepository<DriverAdmin, String>, UserRepository<DriverAdmin> {
    Optional<DriverAdmin> findByEmail(String email);
}
