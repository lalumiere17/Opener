package com.example.demofon.models.api.client;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Client {

    private static final String CONTENT_TYPE  = "application/json;charset=utf-8";
    private static final String USER_AGENT    = "student-app";
    private static final String AUTHORIZATION = "Authorization";
    private static final int TIMEOUT          = 30;

    private OkHttpClient client;
    private MediaType contentType = MediaType.parse(CONTENT_TYPE);

    public Client() {
        client = new OkHttpClient.Builder()
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();

        client.networkInterceptors()
                .add(new UserAgentInterceptor(USER_AGENT));
        client.networkInterceptors()
                .add(new ContentTypeInterceptor(CONTENT_TYPE));
    }

    private boolean isSuccessfulResponse(Response response) {
        if(response == null) return false;

        return response.isSuccessful() && response.code() == 200;
    }

    public String GETRequest(String url, String token) throws IOException {
        String jwtToken = String.format("Bearer %s", token);

        Request request = new Request.Builder()
                .header(AUTHORIZATION, jwtToken)
                .get()
                .url(url)
                .build();

        Response response = client
                .newCall(request)
                .execute();

        //needed edit returned type
        if(isSuccessfulResponse(response))
            return response.body().string();
        else return null;
    }

    public String POSTREquset(String url, String token, String jsonData) throws IOException {
        String jwtToken = String.format("Bearer %s", token);

        RequestBody body = RequestBody
                .create(contentType, jsonData);

        Request request = new Request.Builder()
                .addHeader(AUTHORIZATION, jwtToken)
                .url(url)
                .post(body)
                .build();

        Response response = client
                .newCall(request)
                .execute();

        if(isSuccessfulResponse(response))
            return response.body().string();
        else return null;
    }
}
