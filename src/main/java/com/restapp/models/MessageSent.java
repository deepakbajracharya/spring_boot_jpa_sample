package com.restapp.models;

import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.CascadeType;


@Entity
@Table(name = "messages_sent")
public class MessageSent extends ModelId {

    @Column(name = "msg_text", nullable = false, length = 1024)
    private String message;

    public String getMessage() {
	return message;
    }
    public void setMessage(String _message) {
	message = ModelHelper.trim(_message);
    }

    /**
     * ManyToOne relation used instead of OneToMany from User
     * This prevents loading all MessageSent from User
     * while adding a new one or updating existing one.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    public User getSender() {
	return sender;
    }

    public void setSender(User _sender) {
	this.sender = _sender;
    }

    @Column(name="date_created", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date dateCreated;


    public Date getDateCreated() {
	return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
	this.dateCreated = dateCreated;
    }
    public boolean getIsPaliandrome() {
        if (getMessage() != null) {
            String msg = getMessage();
            return is_paliandrome(msg);
        }
        return false;
    }

    private static boolean is_paliandrome(String str) {
        if (str.length() == 0) {
            return false;
        }

        int len = str.length();
        int half_len = len / 2;
        boolean ret = true;

        for (int i=0; i < half_len; i++) {
            char leftChar = str.charAt(i);
            char rightChar = str.charAt(len - i - 1);
            if ( leftChar != rightChar) {
                ret = false;
                break;
            }
        }
        return ret;
    }

    public String toString() {
	return getId().toString() + " date: " + getDateCreated() + this.getSender().toString() + " msg: " + getMessage();
    }

}
