package com.fashiona.web.client;

import com.fashiona.web.config.KiotVietProperties;
import com.fashiona.web.model.TokenResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KiotVietAuthenticationService {
    private final KiotVietProperties kiotVietProperties;
    private final RestTemplate restTemplate;

    public KiotVietAuthenticationService(KiotVietProperties kiotVietProperties, RestTemplate restTemplate) {
        this.kiotVietProperties = kiotVietProperties;
        this.restTemplate = restTemplate;
    }

    public TokenResponse authentication() {
        return restTemplate.postForObject(kiotVietProperties.getAuthenticationUrl(), kiotVietProperties.authenticationRequestData(), TokenResponse.class);
    }
}
