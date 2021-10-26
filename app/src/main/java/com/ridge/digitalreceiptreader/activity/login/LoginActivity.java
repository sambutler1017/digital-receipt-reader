package com.ridge.digitalreceiptreader.activity.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ridge.digitalreceiptreader.activity.util.NFCEnabledActivity;
import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.service.login.LoginService;

/**
 * Login Activity class for handling functionality with the login screen.
 *
 * @author Sam Butler
 * @author Luke Lengel
 * @since October 18, 2021
 */
public class LoginActivity extends AppCompatActivity {
    private LoginService loginService;
    private Button loginButton;
    private TextView forgotPassword;
    private TextView createUser;

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
        forgotPassword = findViewById(R.id.forgot_password_textView__login);
        createUser = findViewById(R.id.create_account_textView__login);
        loginButton = findViewById(R.id.login_button__login);
    }

    /**
     * Initializes any listeners that are being used in the activity.
     */
    private void initListeners() {
        forgotPassword.setOnClickListener(v -> loginService.onForgotPassword());
        createUser.setOnClickListener(v -> loginService.onCreateUser());
        loginButton.setOnClickListener(v -> loginService.onLogin());
    }

    /**
     * Initializes any services being used by the activity.
     */
    private void initServices() {
        loginService = new LoginService(this);
    }
}