package com.springbaseproject.accountservice.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
public class NoProdProfileTestConfig {

    @PostConstruct
    void validateProfile() {
        String active = System.getProperty("spring.profiles.active");
        if ("prod".equals(active)) {
            throw new IllegalStateException("Tests cannot run with prod profile");
        }
    }
}

