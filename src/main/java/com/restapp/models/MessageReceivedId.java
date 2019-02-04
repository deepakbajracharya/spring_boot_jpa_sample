package com.restapp.models;

import java.util.UUID;
import java.io.Serializable;


public class MessageReceivedId implements Serializable {

    private static final long serialVersionUID = 1L;

    public MessageReceivedId() {
    }

    private UUID messageSent;

    public void setMessageSent(UUID _messageSent) {
	this.messageSent = _messageSent;
    }
    public UUID getMessageSent() {
	return messageSent;
    }

    UUID receiver;

    public void setReceiver(UUID receiver) {
	this.receiver = receiver;
    }
    public UUID getReceiver() {
	return receiver;
    }


    public int hashCode() {
	return (receiver.toString() + messageSent.toString()).hashCode();
    }

    public boolean equals(Object obj) {
	if (obj instanceof MessageReceivedId) {
	    MessageReceivedId msgReceivedId = (MessageReceivedId) obj;

	    return msgReceivedId.receiver.equals(receiver) &&
		msgReceivedId.messageSent.equals(messageSent);
	}
	return false;
    }
}
