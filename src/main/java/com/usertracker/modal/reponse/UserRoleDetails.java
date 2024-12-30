package com.usertracker.modal.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
public class UserRoleDetails {

    private UUID userId;
    private UUID roleId;
    private String token;
    private String email;
    private String fullName;
    private String roleName;
    private String roleTagEnum;
    private String employeeId;
}