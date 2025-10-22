package ru.format.demo.organizationservice.gw.input.rest;

import org.springframework.http.HttpHeaders;

public class UserContext {

    private static final ThreadLocal<String> correlationId = new ThreadLocal<>();
    private static final ThreadLocal<String> authToken = new ThreadLocal<>();
    private static final ThreadLocal<String> userId = new ThreadLocal<>();
    private static final ThreadLocal<String> organizationId = new ThreadLocal<>();

    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN = "tmx-auth-token";
    public static final String USER_ID = "tmx-user-id";
    public static final String ORGANIZATION_ID = "tmx-organization-id";

    public static String getCorrelationId() {
        return correlationId.get();
    }

    public static void setCorrelationId(String cid) {
        correlationId.set(cid);
    }

    public static String getAuthToken() {
        return authToken.get();
    }

    public static void setAuthToken(String aToken) {
        authToken.set(aToken);
    }

    public static String getUserId() {
        return userId.get();
    }

    public static void setUserId(String aUser) {
        userId.set(aUser);
    }

    public static String getOrganizationId() {
        return organizationId.get();
    }

    public static void setOrganizationId(String aOrg) {
        organizationId.set(aOrg);
    }

    public static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(CORRELATION_ID, getCorrelationId());
        return headers;
    }
}
