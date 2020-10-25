package com.fashiona.web.service;

import com.fashiona.web.client.KiotVietAuthenticationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KiotVietKiotVietAuthenticationServiceTest {
    @Autowired
    private KiotVietAuthenticationService kiotVietAuthenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void assertTokenTypeBearer() {
        Assertions.assertEquals(kiotVietAuthenticationService.authentication().getTokenType(), "Bearer");
    }
}