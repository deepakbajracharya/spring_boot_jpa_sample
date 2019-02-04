package com.restapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import com.restapp.models.User;
import com.restapp.repository.UserRepository;


@Component
public class UserInitCommandLineRunner implements CommandLineRunner {

    /**
     * CommandLineRunner component.
     * This is used to load Users at the start of the application.
     */

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String ... args) throws Exception {

        User sender1 = new User();
        sender1.setUsername("Sender_1");
        sender1.setFullname("Sender-One");
        userRepository.save(sender1);

        User receiver1 = new User();
        receiver1.setUsername("Receiver_1");
        receiver1.setFullname("Receiver-One");
        userRepository.save(receiver1);


        User receiver2 = new User();
        receiver2.setUsername("Receiver_2");
        receiver2.setFullname("Receiver-Two");
        userRepository.save(receiver2);

    }

}
