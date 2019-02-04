package com.restapp.repository;

import java.lang.Iterable;

import javax.transaction.Transactional;

import java.util.Optional;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Collections;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageSentRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageSentRepository messageSentRepository;

    @Autowired
    MessageReceivedRepository messageReceivedRepository;

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
    public void testAddMessage() {
	MessageSent msgSent = buildMessage();
	msgSent = messageSentRepository.save(msgSent);

	Optional<MessageSent> optMsgSent0 = messageSentRepository.findById(msgSent.getId());
	Assert.assertEquals( optMsgSent0.isPresent(), true);
	Assert.assertEquals( optMsgSent0.get().getId().toString(), msgSent.getId().toString());
    }



    @Test
    @Transactional
    public void testSendMessage() {
	MessageSent msgSent = buildMessage();
	msgSent = messageSentRepository.save(msgSent);


	MessageReceived msgReceived1 = new MessageReceived();
	msgReceived1.setReceiver( receiver1);
	msgReceived1.setMessageSent(msgSent);
	msgReceived1 = messageReceivedRepository.save(msgReceived1);


	MessageReceived msgReceived2 = new MessageReceived();
	msgReceived2.setReceiver( receiver2);
	msgReceived2.setMessageSent(msgSent);
	msgReceived2 = messageReceivedRepository.save(msgReceived2);


	Set<String> receiverIds =
	    new HashSet<String>(
				Arrays.asList( msgReceived1
					       .getReceiver().getId().toString(),
					       msgReceived2
					       .getReceiver().getId().toString()));

	Iterable<MessageReceived> itMsgReceived =
	    messageReceivedRepository.findByMessageSent(msgSent);

	for(MessageReceived msgReceived: itMsgReceived) {
	    Assert.assertTrue(receiverIds
			      .contains(msgReceived
					.getReceiver().getId().toString()));
	}

        Iterable<MessageSent> itMsgSent =
            messageSentRepository.findBySender(sender1);
        int msgCount = 0;
        System.out.println("after itmsgsent");
        for(MessageSent mSent: itMsgSent) {
            msgCount++;
        }
        Assert.assertTrue(msgCount == 1);
    }

}
