package com.usertracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

import com.usertracker.modal.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmailIgnoreCase(String email);

    @Query("""
        SELECT u FROM User u
        JOIN u.roles r
        WHERE u.email = :email
         """)
    Optional<User> findRoleByEmail(@Param("email") String email);
}
