package com.restapp.controller;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.restapp.models.User;
import com.restapp.models.MessageSent;
import com.restapp.models.MessageReceived;

import com.restapp.service.MessageService;

import com.fasterxml.jackson.databind.JsonNode;


@RestController
public class MessagingController {



    @Autowired
    private MessageService msgService;

    @PostMapping("/user/{username}/send-message")
    public MessageSent sendMessage(@PathVariable String username,
                                   @RequestBody JsonNode requestBody) {
        if ( requestBody.has("message") && requestBody.has("receivers") ) {
            String message = requestBody.get("message").asText();
            JsonNode receiversNode = requestBody.get("receivers");
            if (receiversNode.isArray()) {
                List<User> lsReceivers = new LinkedList<>();
                Iterator<JsonNode> itReceivers = receiversNode.elements();

                System.out.println("ls receivers node.." + itReceivers);
                while(itReceivers.hasNext()) {
                    String receiverUsername = itReceivers.next().asText();
                    User receiver = new User();
                    receiver.setUsername(receiverUsername);
                    lsReceivers.add( receiver);
                }

                User sender = new User();
                sender.setUsername(username);
                MessageSent msgSent = msgService.postMessage(sender,
                                                             message, lsReceivers.toArray( new User[ lsReceivers.size()]));
                return msgSent;
            }
        }
        return null;
    }

    @GetMapping("/user/{username}/sent-messages")
    public List<MessageSent> getMessagesSent(@PathVariable String username) {
        User sender = new User();
        sender.setUsername(username);
        List<MessageSent> lsMessagesSent = msgService.listMessages(sender);

        return lsMessagesSent;
    }

    @DeleteMapping("/user/{username}/sent-messages/{messageId}")
    public String deleteSentMessage(@PathVariable String username,
                                    @PathVariable String messageId) {
        User sender = new User();
        sender.setUsername(username);

        boolean ret = msgService.deleteMessageSent(sender, messageId);
        if ( ret) {
            return "Message Deleted: " + messageId;
        }
        return "Failed to delete the message";
    }

}
