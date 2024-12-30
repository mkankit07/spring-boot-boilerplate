package com.usertracker.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.usertracker.utils.JwtHelper;
import jakarta.annotation.PostConstruct;

@Configuration
public class StaticBeanConfiguration {
    @Value("${jwt.secret.key}")
    String jwtSecretKey;

    @Value("${jwt.expiry.time}")
    Long jwtTokenExpiryTimeInMillis;

    @PostConstruct
    public void init(){
        JwtHelper.setJwtSecretKey(jwtSecretKey);
        JwtHelper.setJwtTokenExpiryTimeInMillis(jwtTokenExpiryTimeInMillis);
    }

}
