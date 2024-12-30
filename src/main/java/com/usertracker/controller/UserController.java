package com.usertracker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usertracker.modal.reponse.APIResponse;
import com.usertracker.modal.request.LoginRequest;
import com.usertracker.service.UserService;
import com.usertracker.utils.ResponseUtils;
import com.usertracker.utils.Translator;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;
    private final Translator translator;


    @PostMapping(value = "/login")
    ResponseEntity<APIResponse> authenticateUser(final @Valid @RequestBody LoginRequest loginRequest) {

        log.info("Inside the Login Controller");
        return ResponseUtils.okResponse(translator.toLocal("login.success"),
            userService.authenticateUser(loginRequest));
    }
}
