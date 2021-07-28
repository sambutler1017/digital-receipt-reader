package com.ridge.digitalreceiptreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText emailInput;
    private EditText passwordInput;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = (EditText)findViewById(R.id.email_textbox__login);
        passwordInput = (EditText) findViewById(R.id.password_textbox__login);
        loginButton = (Button)findViewById(R.id.login_button__login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogin(emailInput.getText().toString(), passwordInput.getText().toString());
            }
        });
    }

    private void onLogin(String email, String password) {
        if(email.equals("test") && password.equals("password")) {
           Intent intent = new Intent(LoginActivity.this, MainActivity.class);
           startActivity(intent);
        } else {
            loginButton.setText("Invalid Credentials");
        }
    }
}