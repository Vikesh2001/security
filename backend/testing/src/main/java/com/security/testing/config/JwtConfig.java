package com.security.testing.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.util.Base64;

@Configuration
public class JwtConfig {

    @Bean
    public String getSecret() {
        // Generate a secure key for HS512
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        // Encode to Base64 for use in JwtTokenUtil
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    @Bean
    public long getExpirationInMs() {
        // 24 hours in milliseconds
        return 24 * 60 * 60 * 1000L;
    }
}