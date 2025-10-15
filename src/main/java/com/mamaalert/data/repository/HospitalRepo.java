package com.mamaalert.data.repository;

import com.mamaalert.data.model.Hospital;
import com.mongodb.MongoCredential;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HospitalRepo extends MongoRepository<Hospital, String> {
}
