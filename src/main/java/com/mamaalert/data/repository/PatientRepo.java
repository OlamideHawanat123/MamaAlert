package com.mamaalert.data.repository;

import com.mamaalert.data.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PatientRepo extends MongoRepository<Patient, String>, UserRepository<Patient> {
    Optional<Patient> findByEmail(String email);
}