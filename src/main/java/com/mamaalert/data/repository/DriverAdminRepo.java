package com.mamaalert.data.repository;

import com.mamaalert.data.model.DriverAdmin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DriverAdminRepo extends MongoRepository<DriverAdmin, String> {
}
