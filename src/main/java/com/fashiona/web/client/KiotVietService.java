package com.fashiona.web.client;

import com.fashiona.web.model.TokenResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.function.Supplier;

@Service
public class KiotVietService {
    public static String AUTHORIZATION = "";

    private final RestTemplate restTemplate;
    private final KiotVietAuthenticationService kiotVietAuthenticationService;

    public KiotVietService(RestTemplate restTemplate, KiotVietAuthenticationService kiotVietAuthenticationService) {
        this.restTemplate = restTemplate;
        this.kiotVietAuthenticationService = kiotVietAuthenticationService;
    }

    public static void setAuthorization(String authorization) {
        KiotVietService.AUTHORIZATION = authorization;
    }

    public <T> T post(String url, @Nullable Object request, Class<T> responseType) {
        return this.exchange(() -> restTemplate.postForObject(url, request, responseType));
    }

    public <T> T get(String url, Class<T> responseType) {
        return this.exchange(() -> restTemplate.getForObject(url, responseType));
    }

    public <T> T get(String url, ParameterizedTypeReference<T> responseType) {
        return this.exchange(() -> restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, responseType).getBody());
    }

    private  <T> T exchange(Supplier<T> supplier) {
        this.checkAccessToken();

        try {
            return supplier.get();
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                return this.authorizationRetry(supplier);
            }

            throw exception;
        }
    }

    private void checkAccessToken() {
        if (StringUtils.isEmpty(KiotVietService.AUTHORIZATION)) {
            TokenResponse authentication = kiotVietAuthenticationService.authentication();

            KiotVietService.setAuthorization(authentication.getAccessToken());
        }
    }

    private <T> T authorizationRetry(Supplier<T> supplier) {
        TokenResponse authentication = kiotVietAuthenticationService.authentication();

        KiotVietService.setAuthorization(StringUtils.join(authentication.getTokenType(), StringUtils.SPACE, authentication.getAccessToken()));

        return supplier.get();
    }
}
