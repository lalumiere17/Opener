package com.example.demofon.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demofon.R;
import com.example.demofon.MainActivity;

import static android.view.View.OnClickListener;

public class LoginFragment extends Fragment {

    private TextInputEditText loginView;
    private TextInputEditText passwordView;

    private TextView buttonSignIn;

    private MainActivity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.login_fragment, container, false);

        activity = (MainActivity) getActivity();

        loginView = fragment.findViewById(R.id.login_view);
        passwordView = fragment.findViewById(R.id.password_view);
        buttonSignIn = fragment.findViewById(R.id.button_sign_in);

        buttonSignIn.setOnClickListener(buttonSignInListener);

        return fragment;
    }

    private OnClickListener buttonSignInListener = v -> {
        activity.showProgress(null);
    };
}
