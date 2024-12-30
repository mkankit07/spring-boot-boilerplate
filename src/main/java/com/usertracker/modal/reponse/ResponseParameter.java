package com.usertracker.modal.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@AllArgsConstructor
@Getter
public class ResponseParameter<T> {

    T response;
    PaginationParameter paginationParameters;
}