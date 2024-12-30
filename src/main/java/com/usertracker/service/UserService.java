package com.usertracker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usertracker.exceptions.DataNotFoundException;
import com.usertracker.mapper.UserMapper;
import com.usertracker.modal.entity.User;
import com.usertracker.modal.reponse.UserRoleDetails;
import com.usertracker.modal.request.LoginRequest;
import com.usertracker.repository.UserRepository;
import com.usertracker.utils.JwtHelper;
import com.usertracker.utils.Translator;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final Translator translator;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public UserRoleDetails authenticateUser(final LoginRequest loginRequest) {

        log.info("Inside the authenticateUser() of UserService.java");

        authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        final String accessToken = JwtHelper.generateToken(loginRequest);

        log.info("Outside from Login Service");

        return userMapper.userRoleDetailsToUserRole(userRepository.findRoleByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new DataNotFoundException("user.email.not.found")), accessToken);
    }
}
