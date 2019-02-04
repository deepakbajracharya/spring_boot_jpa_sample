package com.restapp.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.IdClass;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;
import javax.persistence.Table;
import javax.persistence.CascadeType;

@Entity
@Table(
       name = "message_received",
       uniqueConstraints=@UniqueConstraint(columnNames={"receiver_id", "message_sent_id"})
       )
public class MessageReceived  extends ModelId {


    @ManyToOne
    @JoinColumn(name="receiver_id", nullable = false)
    private User receiver;

    public User getReceiver() {
	return receiver;
    }

    public void setReceiver(User receiver) {
	this.receiver = receiver;
    }


    @ManyToOne
    @JoinColumn(name="message_sent_id", nullable = false)
    private MessageSent messageSent;

    public MessageSent getMessageSent() {
	return messageSent;
    }

    public void setMessageSent(MessageSent message) {
	this.messageSent = message;
    }

}
