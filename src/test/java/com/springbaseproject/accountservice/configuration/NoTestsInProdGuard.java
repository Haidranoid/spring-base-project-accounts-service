package com.springbaseproject.accountservice.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
class NoTestsInProdGuard {

    @PostConstruct
    void failFast() {
        if (isTestEnvironment()) {
            throw new IllegalStateException("""
                Tests must NOT run with 'prod' profile
                Set SPRING_PROFILES_ACTIVE=test or it
            """);
        }
    }

    private boolean isTestEnvironment() {
        return StackWalker.getInstance()
                .walk(frames -> frames.anyMatch(f ->
                        f.getClassName().contains("org.junit")
                ));
    }
}

