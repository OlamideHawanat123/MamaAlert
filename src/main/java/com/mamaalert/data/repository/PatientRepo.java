package com.mamaalert.data.repository;

import com.mamaalert.data.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepo extends MongoRepository<Patient, String> {
}
