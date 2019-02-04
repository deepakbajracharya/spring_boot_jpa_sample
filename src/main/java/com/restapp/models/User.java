package com.restapp.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import java.util.Collection;


@Entity
public class User extends ModelId {

    private String username;

    public String getUsername() {
	return username;
    }
    public void setUsername(String _username) {
	username = ModelHelper.trim(_username);
    }


    private String fullname;


    public String getFullname() {
	return fullname;
    }
    public void setFullname(String _fullname) {
	fullname = ModelHelper.trim(_fullname);
    }

    public String toString() {
	return getId() + " username: " + getUsername() + " fullname: " + getFullname();
    }
}
