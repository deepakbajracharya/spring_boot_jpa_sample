package com.restapp.repository;


import java.util.UUID;
import java.util.Optional;

import com.restapp.models.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Page<User> findById(UUID id, Pageable pageable);
    Optional<User> findByUsername(String username);
}


