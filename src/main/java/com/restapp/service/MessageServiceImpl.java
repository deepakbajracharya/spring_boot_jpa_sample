package com.restapp.service;

import java.lang.Iterable;
import java.util.List;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Date;
import java.util.UUID;


import com.restapp.models.User;
import com.restapp.models.MessageSent;
import com.restapp.models.MessageReceived;

import com.restapp.repository.UserRepository;
import com.restapp.repository.MessageSentRepository;
import com.restapp.repository.MessageReceivedRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MessageServiceImpl implements MessageService {


    @Autowired UserRepository userRepository;
    @Autowired MessageSentRepository messageSentRepository;
    @Autowired MessageReceivedRepository messageReceivedRepository;

    @Transactional
    public MessageSent postMessage(User _sender, String message, User [] receivers) {
        String username = _sender.getUsername();
        Optional<User> optSender = userRepository.findByUsername(username);

        if (optSender.isPresent()) {
            User sender = optSender.get();
            MessageSent msgSent = new MessageSent();
            msgSent.setMessage(message);
            msgSent.setSender(sender);
            msgSent.setDateCreated(new Date());
            msgSent = messageSentRepository.save(msgSent);

            for(User _receiver: receivers) {
                Optional<User> optReceiver = userRepository.findByUsername(_receiver.getUsername());
                if (optReceiver.isPresent()) {
                    User receiver = optReceiver.get();
                    MessageReceived msgReceived = new MessageReceived();
                    msgReceived.setReceiver( receiver);
                    msgReceived.setMessageSent( msgSent);
                    msgReceived = messageReceivedRepository.save(msgReceived);

                }
            }
            return msgSent;

        }
        return null;
    }

    @Transactional
    public List<MessageSent> listMessages(User user) {
        List<MessageSent> lsMsgSent = new LinkedList<>();
        Optional<User> optSender = userRepository.findByUsername(user.getUsername());
        if (optSender.isPresent()) {
            User sender = optSender.get();
            Iterable<MessageSent> itMessageSent =
                messageSentRepository.findBySender(sender);
            for(MessageSent msgSent: itMessageSent) {
                lsMsgSent.add(msgSent);
            }

        }
        return lsMsgSent;
    }

    @Transactional
    public boolean deleteMessageSent(User user, String messageSentId) {
        Optional<User> optSender = userRepository.findByUsername(user.getUsername());
        if (optSender.isPresent()) {
            User sender = optSender.get();
            UUID msgSentId = UUID.fromString(messageSentId);
            Optional<MessageSent> optMsgSent = messageSentRepository
                .findBySenderAndId(sender, msgSentId);

            if ( optMsgSent.isPresent()) {
                MessageSent msgSent = optMsgSent.get();

                Iterable<MessageReceived> itMessagesReceived =
                    messageReceivedRepository.findByMessageSent(msgSent);
                for(MessageReceived msgReceived: itMessagesReceived) {
                    messageReceivedRepository.delete(msgReceived);
                }
                messageSentRepository.delete(msgSent);
                return true;
            }
        }
        return false;
    }

}
