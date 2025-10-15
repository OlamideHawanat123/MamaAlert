package com.mamaalert.data.repository;

import com.mamaalert.data.model.Hospital;
import com.mongodb.MongoCredential;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface HospitalRepo extends MongoRepository<Hospital, String>, UserRepository<Hospital> {
    Optional<Hospital> findByEmail(String email);
}
