package com.mamaalert.data.repository;

import com.mamaalert.data.model.SuperAdmin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperAdminRepo extends MongoRepository<SuperAdmin, String> {
}
