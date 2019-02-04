package com.restapp.service;

import java.util.List;

import com.restapp.models.User;
import com.restapp.models.MessageSent;

public interface MessageService {

    public MessageSent postMessage(User sender, String message, User [] receivers);

    public List<MessageSent> listMessages(User user);

    public boolean deleteMessageSent(User username, String messageSentId);

}
