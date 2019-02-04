package com.restapp.repository;


import java.util.UUID;
import java.util.Optional;
import java.lang.Iterable;

import com.restapp.models.User;
import com.restapp.models.MessageSent;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageSentRepository extends PagingAndSortingRepository<MessageSent, UUID> {

    Page<MessageSent> findById(UUID id, Pageable pageable);
    Iterable<MessageSent> findBySender(User sender);
    Optional<MessageSent> findBySenderAndId(User sender, UUID id);
}


