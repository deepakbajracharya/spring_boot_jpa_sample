package com.restapp.repository;


import java.util.UUID;
import java.util.Optional;
import java.lang.Iterable;

import com.restapp.models.User;
import com.restapp.models.MessageSent;
import com.restapp.models.MessageReceived;
import com.restapp.models.MessageReceivedId;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageReceivedRepository extends PagingAndSortingRepository<MessageReceived, UUID> {

    Page<MessageReceived> findById(UUID id, Pageable pageable);
    Iterable<MessageReceived> findByReceiver(User receiver);
    Iterable<MessageReceived> findByMessageSent(MessageSent messageSent);
}


