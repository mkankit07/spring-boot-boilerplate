package com.usertracker.constants.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;
import java.util.stream.Stream;

import com.usertracker.exceptions.DataValidationException;

@AllArgsConstructor
@Getter
public enum Status {

    ACTIVE(1, "Active"),
    DELETED(0, "Deleted");

    private final int value;
    private final String displayName;

    public static Status of(final Integer value) {

        return Optional.ofNullable(value)
            .map(integer -> Stream.of(Status.values())
                .filter(p -> p.getValue() == value)
                .findFirst()
                .orElseThrow(() -> new DataValidationException("Invalid enum value")))
            .orElse(null);
    }
}
