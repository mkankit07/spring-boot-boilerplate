package com.usertracker.modal.reponse;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@Builder
@JsonInclude(NON_EMPTY)
@Getter
public class APIErrorResponse {

    private int code;

    private String message;

    private List<Error> error;

    @Builder
    @JsonInclude(NON_EMPTY)
    @Getter
    public static class Error {

        private String field;

        private String message;
    }
}
