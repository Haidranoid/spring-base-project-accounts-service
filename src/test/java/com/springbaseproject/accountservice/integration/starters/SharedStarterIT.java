package com.springbaseproject.accountservice.integration.starters;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SharedStarterIT {

    @Autowired
    JwtDecoder decoder;

    @Test
    void jwtDecoderIsConfigured() {
        assertNotNull(decoder);
    }
}

