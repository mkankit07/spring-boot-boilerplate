package com.usertracker.mapper;


import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import java.util.Set;

import com.usertracker.modal.entity.Role;
import com.usertracker.modal.entity.User;
import com.usertracker.modal.reponse.UserRoleDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
public interface UserMapper {


    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "roleId", expression = "java(getUserRoleDetails(user.getRoles()).getId())")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "roleName", expression = "java(getUserRoleDetails(user.getRoles()).getName())")
    @Mapping(target = "roleTagEnum", expression = "java(getUserRoleDetails(user.getRoles()).getSystemName())")
    @Mapping(target = "fullName", source = "user.fullName")
    UserRoleDetails userRoleDetailsToUserRole(User user, String token);

    default Role getUserRoleDetails(Set<Role> roles) {
        return roles.stream()
            .findFirst()
            .orElse(null);
    }
}
