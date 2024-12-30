package com.usertracker.exceptions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.usertracker.constants.enums.SystemResponseStatus;
import com.usertracker.modal.reponse.APIErrorResponse;
import com.usertracker.utils.Translator;
import jakarta.validation.ValidationException;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    private static final String REQUEST_BODY_IS_REQUIRED_OR_INVALID_REQUEST_BODY = "Request body is required or Invalid Request body";
    private static final String SERVICE_UNAVAILABLE = "Service Unavailable";
    private static final String INVALID_FIELDS = "Invalid Fields";

    private final Translator translator;

    @ExceptionHandler(value = {DataNotFoundException.class})
    protected ResponseEntity<APIErrorResponse> handleDataNotFoundException(final RuntimeException ex) {

        log.info("Inside the handleDataNotFoundException");

        final APIErrorResponse apiErrorResponse = APIErrorResponse.builder()
            .code(SystemResponseStatus.NOT_FOUND.getCode())
            .message(ex.getMessage())
            .build();

        log.atInfo().log(ex.getClass().getName(), apiErrorResponse.toString());

        log.info("Completed the handleDataNotFoundException", ex);

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<APIErrorResponse> handleRuntimeException(final RuntimeException ex) {

        log.info("Inside the handleRuntimeException");

        final APIErrorResponse apiErrorResponse = APIErrorResponse.builder()
            .message(SERVICE_UNAVAILABLE)
            .code(SystemResponseStatus.INTERNAL_SERVER_ERROR.getCode())
            .build();

        log.atInfo().log(ex.getClass().getName(), apiErrorResponse.toString());
        log.info("Completed the handleRuntimeException", ex);

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = AuthenticationException.class)
    protected ResponseEntity<APIErrorResponse> handleAuthenticationException(final RuntimeException ex) {

        log.info("Inside the handleAuthenticationException");

        final APIErrorResponse apiErrorResponse = APIErrorResponse.builder()
            .code(SystemResponseStatus.INVALID_CREDENTIALS.getCode())
            .message(translator.toLocal("invalid.jwt.token"))
            .build();

        log.atInfo().log(ex.getClass().getName(), apiErrorResponse.toString());
        log.info("Completed the handleAuthenticationException");

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {DataValidationException.class, ValidationException.class})
    protected ResponseEntity<APIErrorResponse> handleValidationFailedException(final RuntimeException ex) {

        log.info("Inside the handleValidationFailedException");

        final APIErrorResponse apiErrorResponse = APIErrorResponse.builder()
            .message(ex.getMessage())
            .code(SystemResponseStatus.INVALID_DATA.getCode())
            .build();

        log.atInfo().log(ex.getClass().getName(), apiErrorResponse.toString());
        log.info("Completed the handleValidationFailedException");

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = IllegalArgumentException.class)
    private ResponseEntity<APIErrorResponse> handleIllegalArgumentException(final RuntimeException exception) {

        final APIErrorResponse apiErrorResponse = APIErrorResponse.builder()
            .message(exception.getMessage())
            .code(SystemResponseStatus.INVALID_DATA.getCode())
            .build();

        log.atInfo().log("Inside the handleIllegalArgumentException");
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
