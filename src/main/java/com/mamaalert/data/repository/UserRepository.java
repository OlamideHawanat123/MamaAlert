package com.mamaalert.data.repository;

import com.mamaalert.data.model.User;

import java.util.Optional;

public interface UserRepository<T extends User> {
    Optional<T> findByEmail(String email);
}

