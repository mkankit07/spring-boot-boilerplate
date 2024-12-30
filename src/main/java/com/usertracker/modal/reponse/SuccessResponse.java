package com.usertracker.modal.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@AllArgsConstructor
@Getter
public class SuccessResponse<T, K> {

    private Integer code;
    private String message;
    private T data;
    private K metaData;
}

