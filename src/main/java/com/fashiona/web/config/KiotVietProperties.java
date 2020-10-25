package com.fashiona.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@ConfigurationProperties(prefix = "kiotviet")
public class KiotVietProperties {
    private String authenticationUrl;
    private String publicUrl;
    private String retailer;
    private String scope;
    private String grantType;
    private String clientId;
    private String clientSecret;

    public String getAuthenticationUrl() {
        return authenticationUrl;
    }

    public void setAuthenticationUrl(String authenticationUrl) {
        this.authenticationUrl = authenticationUrl;
    }

    public String getPublicUrl() {
        return publicUrl;
    }

    public void setPublicUrl(String publicUrl) {
        this.publicUrl = publicUrl;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public MultiValueMap<String, String> authenticationRequestData() {
        MultiValueMap<String, String> authenticationData = new LinkedMultiValueMap<>();
        authenticationData.add("scope", this.getScope());
        authenticationData.add("grant_type", this.getGrantType());
        authenticationData.add("client_id", this.getClientId());
        authenticationData.add("client_secret", this.getClientSecret());

        return authenticationData;
    }
}
