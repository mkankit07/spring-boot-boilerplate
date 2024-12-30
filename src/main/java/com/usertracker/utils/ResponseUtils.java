package com.usertracker.utils;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.usertracker.modal.reponse.APIResponse;
import com.usertracker.modal.reponse.PaginationParameter;
import com.usertracker.modal.reponse.ResponseParameter;
import com.usertracker.modal.reponse.SuccessResponse;

@UtilityClass
public class ResponseUtils {

    public ResponseEntity<APIResponse> createdResponse(final String message, final Object data) {
        final APIResponse apiResponse = new APIResponse(HttpStatus.CREATED.value(), message, data);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    public ResponseEntity<APIResponse> okResponse(final String message, final Object data) {
        final APIResponse apiResponse = new APIResponse(HttpStatus.OK.value(), message, data);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    public ResponseEntity<APIResponse> noContentResponse(final String message) {
        final APIResponse apiResponse = new APIResponse(HttpStatus.NO_CONTENT.value(), message, null);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    public <T> ResponseEntity<SuccessResponse<T, PaginationParameter>>
    createPaginatedResponse(final String message, final ResponseParameter<T> responseParameter) {

        final SuccessResponse<T, PaginationParameter> paginationParameterSuccessResponse = SuccessResponse.<T, PaginationParameter>builder()
            .code(HttpStatus.OK.value())
            .message(message)
            .data(responseParameter.getResponse())
            .metaData(responseParameter.getPaginationParameters())
            .build();

        return ResponseEntity.status(HttpStatus.OK).body(paginationParameterSuccessResponse);
    }
}