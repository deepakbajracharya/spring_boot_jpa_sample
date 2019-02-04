package com.restapp.service;

import java.util.List;
import java.util.Date;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.Assert;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.restapp.models.User;
import com.restapp.models.MessageSent;
import com.restapp.models.MessageReceived;

import com.restapp.repository.UserRepository;
import com.restapp.repository.MessageSentRepository;
import com.restapp.repository.MessageReceivedRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageSentRepository messageSentRepository;

    @Autowired
    MessageReceivedRepository messageReceivedRepository;


    @Autowired
    MessageService messageService;

    User sender1, receiver1, receiver2;

    @Before
    public void setup() {
	sender1 = new User();
	sender1.setUsername("sender1");
	sender1.setFullname("Sender 1");


	receiver1 = new User();
	receiver1.setUsername("receiver1");
	receiver1.setFullname("Receiver 1");

	receiver2 = new User();
	receiver2.setUsername("receiver2");
	receiver2.setFullname("Receiver 2");

	sender1 = userRepository.save(sender1);
	receiver1 = userRepository.save(receiver1);
	receiver2 = userRepository.save(receiver2);
    }

    @After
    public void removeAll() {
	messageReceivedRepository.deleteAll();
	messageSentRepository.deleteAll();
	userRepository.deleteAll();
    }

    private MessageSent buildMessage() {
	MessageSent msgSent = new MessageSent();
	msgSent.setMessage("Hello World!");
	msgSent.setSender(sender1);
	msgSent.setDateCreated( new Date());
	return msgSent;
    }


    @Test
    public void testPostMessage() {
        messageService.postMessage(sender1,
                                   "Posing about spring service",
                                   new User[]{ receiver1, receiver2});

        List<MessageSent> lsMsgSent = messageService.listMessages(sender1);
        Assert.assertTrue(1 == lsMsgSent.size());

    }

    @Test
    public void testDeleteMessage() {
        messageService.postMessage(sender1,
                                   "Posing about spring service",
                                   new User[]{ receiver1, receiver2});

        List<MessageSent> lsMsgSent = messageService.listMessages(sender1);
        Assert.assertTrue(1 == lsMsgSent.size());

        for(MessageSent msgSent: lsMsgSent) {
            messageService.deleteMessageSent(sender1, msgSent.getId().toString());
        }
        lsMsgSent = messageService.listMessages(sender1);
        Assert.assertTrue(lsMsgSent.isEmpty());

    }
}
