package com.restapp.models;

import java.util.UUID;
import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
   This class can be extended by any model which needs UUID
   for an ID
 */

@MappedSuperclass
abstract class ModelId {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    public UUID getId() {
	return id;
    }

    public void setId(UUID id) {
	this.id = id;
    }

}
