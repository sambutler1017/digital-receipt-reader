package com.ridge.digitalreceiptreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;

import com.ridge.digitalreceiptreader.service.login.LoginService;

/**
 * Login Activity class for handling functionality with the login screen.
 *
 * @author Sam Butler
 * @since July 28, 2021
 */
public class LoginActivity extends AppCompatActivity {
    private LoginService loginService;

    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialization();
    }

    /**
     * Starting initialization of the activity.
     */
    private void initialization() {
        initElements();
        initListeners();
        initServices();
    }

    /**
     * Initializes any elements that are being used in the activity.
     */
    private void initElements() {
        loginButton = findViewById(R.id.login_button__login);
    }

    /**
     * Initializes any listeners that are being used in the activity.
     */
    private void initListeners() {
        loginButton.setOnClickListener(v -> loginService.onLogin());
    }

    /**
     * Initializes any services being used by the activity.
     */
    private void initServices() {
        loginService = new LoginService(LoginActivity.this);
    }
}