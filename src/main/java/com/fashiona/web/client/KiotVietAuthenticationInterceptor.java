package com.fashiona.web.client;

import com.fashiona.web.config.KiotVietProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class KiotVietAuthenticationInterceptor implements ClientHttpRequestInterceptor {
    private final KiotVietProperties kiotVietProperties;

    public KiotVietAuthenticationInterceptor(KiotVietProperties kiotVietProperties) {
        this.kiotVietProperties = kiotVietProperties;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("Retailer", kiotVietProperties.getRetailer());
        request.getHeaders().add(HttpHeaders.AUTHORIZATION, KiotVietService.AUTHORIZATION);

        return execution.execute(request, body);
    }
}