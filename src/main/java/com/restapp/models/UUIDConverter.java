package com.restapp.models;

import java.util.UUID;

import javax.persistence.Converter;
import javax.persistence.AttributeConverter;

@Converter(autoApply = true)
public class UUIDConverter implements AttributeConverter<UUID, String> {

    public String convertToDatabaseColumn(UUID uuid) {
	return uuid.toString();
    }

    public UUID convertToEntityAttribute(String db_uuid) {
	return UUID.fromString(db_uuid);
    }
}
