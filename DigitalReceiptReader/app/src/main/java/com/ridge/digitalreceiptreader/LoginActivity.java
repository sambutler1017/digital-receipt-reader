package com.ridge.digitalreceiptreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.ridge.digitalreceiptreader.service.toast.ToastService;

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

        emailInput = (EditText)findViewById(R.id.email_textbox__login);
        passwordInput = (EditText) findViewById(R.id.password_textbox__login);
        loginButton = (Button)findViewById(R.id.login_button__login);
        toastService = new ToastService(this);
        loadingIndicator = (ProgressBar)findViewById(R.id.loading_indicator__login);
        loadingIndicator.setVisibility(View.GONE);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogin(emailInput.getText().toString(), passwordInput.getText().toString());
            }
        });
    }

    private void onLogin(String email, String password) {
        if(email.equals("test") && password.equals("password")) {
            loadingIndicator.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.GONE);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    toastService.showSuccess("Logged in Successfully!");
                }
            }, 2000);

        } else {
            toastService.showError("Invalid Credentials!");
        }
    }
}