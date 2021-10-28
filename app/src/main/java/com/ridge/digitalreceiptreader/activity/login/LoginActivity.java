package com.ridge.digitalreceiptreader.activity.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.login.module.LoginModule;

/**
 * Login Activity class for handling functionality with the login screen.
 *
 * @author Sam Butler & Luke Lengel
 * @since October 18, 2021
 */
public class LoginActivity extends AppCompatActivity {
    private LoginModule loginModule;
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
        forgotPassword.setOnClickListener(v -> loginModule.onForgotPassword());
        createUser.setOnClickListener(v -> loginModule.onCreateUser());
        loginButton.setOnClickListener(v -> loginModule.onLogin());
    }

    /**
     * Initializes any services being used by the activity.
     */
    private void initServices() {
        loginModule = new LoginModule(this);
    }
}