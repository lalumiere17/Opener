package com.example.demofon.models.api.client;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class UserAgentInterceptor implements Interceptor {

    private String userAgent;

    public UserAgentInterceptor(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request requestWithUserAgent = request
                .newBuilder()
                .header("user-agent", userAgent)
                .build();

        return chain.proceed(requestWithUserAgent);
    }
}
