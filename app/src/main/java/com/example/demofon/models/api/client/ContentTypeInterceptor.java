package com.example.demofon.models.api.client;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ContentTypeInterceptor implements Interceptor {

    private String contentType;

    public ContentTypeInterceptor(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request requestWithContentType = request
                .newBuilder()
                .header("content-type", contentType)
                .build();
        return chain.proceed(request);
    }
}
