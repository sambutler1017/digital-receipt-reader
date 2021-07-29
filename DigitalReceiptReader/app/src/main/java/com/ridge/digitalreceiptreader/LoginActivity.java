package com.ridge.digitalreceiptreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ridge.digitalreceiptreader.service.toast.ToastService;

/**
 * Login Activity class for handling functionality with the login screen.
 *
 * @author Sam Butler
 * @since July 28, 2021
 */
public class LoginActivity extends AppCompatActivity {
    private ToastService toastService;

    private EditText emailInput;
    private EditText passwordInput;
    private Button loginButton;
    private ProgressBar loadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initServices();
        initElements();
        initListeners();
    }

    /**
     * Initializes any service classes being used in the activity.
     */
    private void initServices() {
        toastService = new ToastService(this);
    }

    /**
     * Initializes any elements that are being used in the activity.
     */
    private void initElements() {
        emailInput = (EditText)findViewById(R.id.email_textbox__login);
        passwordInput = (EditText) findViewById(R.id.password_textbox__login);
        loginButton = (Button)findViewById(R.id.login_button__login);
        loadingIndicator = (ProgressBar)findViewById(R.id.loading_indicator__login);
        loadingIndicator.setVisibility(View.GONE);
    }

    /**
     * Initializes any listeners that are being used in the activity.
     */
    private void initListeners() {
        loginButton.setOnClickListener(v -> onLogin(v));
    }

    /**
     * Login click event for when the user clicks the login button.
     *
     * @param view Current view of the activity.
     */
    public void onLogin(View view) {
        if(emailInput.getText().toString().equals("test") && passwordInput.getText().toString().equals("password")) {
            loadingIndicator.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.GONE);

            Handler handler = new Handler();
            handler.postDelayed(() -> {
                toastService.showSuccess("Logged in Successfully!");
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                loadingIndicator.setVisibility(View.GONE);
            }, 2000);

        } else {
            toastService.showError("Invalid Credentials!");
        }
    }
}