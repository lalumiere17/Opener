package com.example.demofon.models.api.OAuth2;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;

import com.example.demofon.MainActivity;

import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.ResponseTypeValues;

public class AuthClient {

    public static final int RC_AUTH = 1001;

    private AuthorizationRequest authRequest;

    private Activity activity;

    public AuthClient(Activity activity) {
        AuthorizationServiceConfiguration authConfig = new AuthorizationServiceConfiguration(
                Uri.parse(OAuth2Requisites.AUTHORIZATION_ENDPOINT),
                Uri.parse(OAuth2Requisites.TOKEN_ENDPOINT),
                null
        );

        AuthorizationRequest.Builder authRequestBuilder = new AuthorizationRequest.Builder(
                authConfig,
                OAuth2Requisites.CLIENT_ID,
                ResponseTypeValues.TOKEN,
                Uri.parse(OAuth2Requisites.REDIRECT_URI));

        authRequest = authRequestBuilder.build();

        this.activity = activity;
    }

    public void startAuthService() {
        AuthorizationService authService = new AuthorizationService(activity.getApplicationContext());
//        Intent authIntent = authService.getAuthorizationRequestIntent(authRequest);
//        activity.startActivityForResult(authIntent, RC_AUTH);
        authService.performAuthorizationRequest(
                authRequest,
                PendingIntent.getActivity(activity, 0, new Intent(activity, MainActivity.class), 0),
                PendingIntent.getActivity(activity, 0, new Intent(activity, MainActivity.class), 0));
    }
}
