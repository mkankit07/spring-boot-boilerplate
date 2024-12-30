package com.usertracker.constants.enums.convertor;

import java.util.Optional;

import com.usertracker.constants.enums.Status;
import jakarta.persistence.AttributeConverter;

public class StatusConvertor implements AttributeConverter<Status, Integer> {

    @Override
    public Integer convertToDatabaseColumn(final Status status) {

        return Optional.ofNullable(status)
            .map(Status::getValue)
            .orElse(null);
    }

    @Override
    public Status convertToEntityAttribute(final Integer value) {

        return Status.of(value);
    }
}
