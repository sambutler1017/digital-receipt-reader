package com.ridge.digitalreceiptreader.activity.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.login.module.LoginActivityModule;
import com.ridge.digitalreceiptreader.common.abstracts.BaseActivity;

/**
 * Login Activity class for handling functionality with the login screen.
 *
 * @author Sam Butler & Luke Lengel
 * @since October 18, 2021
 */
public class LoginActivity extends BaseActivity {
    private LoginActivityModule loginActivityModule;
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
     * Initializes any elements that are being used in the activity.
     */
    public void initElements() {
        forgotPassword = findViewById(R.id.forgot_password_textView__login);
        createUser = findViewById(R.id.create_account_textView__login);
        loginButton = findViewById(R.id.login_button__login);
    }

    /**
     * Initializes any listeners that are being used in the activity.
     */
    public void initListeners() {
        forgotPassword.setOnClickListener(v -> loginActivityModule.onForgotPassword());
        createUser.setOnClickListener(v -> loginActivityModule.onCreateUser());
        loginButton.setOnClickListener(v -> loginActivityModule.onLogin());
    }

    /**
     * Initializes any services being used by the activity.
     */
    public void initServices() {
        loginActivityModule = new LoginActivityModule(this);
    }
}