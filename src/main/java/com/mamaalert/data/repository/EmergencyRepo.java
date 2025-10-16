package com.mamaalert.data.repository;

import com.mamaalert.data.model.Emergency;
import com.mamaalert.data.model.EmergencyStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmergencyRepo extends MongoRepository<Emergency, String> {
    List<Emergency> findByStatus(EmergencyStatus emergencyStatus);
}
