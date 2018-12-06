package com.example.demofon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ProgressBar;

import com.auth0.android.jwt.JWT;
import com.example.demofon.models.Settings;
import com.example.demofon.models.api.OAuth2.AuthClient;

import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationResponse;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar);
        settings = new Settings(getApplicationContext());

        checkAuth();
    }

    private void startFragment(Fragment fragment) {
        if(fragment == null) return;

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
    }

    public void showProgress(String message) {
        if(progressBar.getVisibility() == VISIBLE) return;

        progressBar.setVisibility(VISIBLE);
    }

    public void hideProgress() {
        if(progressBar.getVisibility() == INVISIBLE) return;

        progressBar.setVisibility(INVISIBLE);
    }

    public void auth() {
        AuthClient authClient = new AuthClient(this);
        authClient.startAuthService();
    }

    //doesn't work
    private void checkAuth() {
        AuthorizationResponse resp = AuthorizationResponse.fromIntent(getIntent());
        AuthorizationException ex = AuthorizationException.fromIntent(getIntent());
        if (resp != null) {
            String accessToken = resp.accessToken;

            if(TextUtils.isEmpty(accessToken)) {
                settings.setTokenId(Settings.TOKEN_ID_DEFAULT);
                return;
            }

            JWT token = new JWT(accessToken);
            String tokenId = token.getId();
            settings.setTokenId(tokenId);
        }
        else {
            auth();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == AuthClient.RC_AUTH) {
            AuthorizationResponse response = AuthorizationResponse.fromIntent(data);

            String accessToken = response.accessToken;

            if(TextUtils.isEmpty(accessToken)) {
                settings.setTokenId(Settings.TOKEN_ID_DEFAULT);
                return;
            }

            JWT token = new JWT(accessToken);
            String tokenId = token.getId();
            settings.setTokenId(tokenId);
        }
    }
}
