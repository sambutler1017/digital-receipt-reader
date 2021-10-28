package com.ridge.digitalreceiptreader.activity.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.login.module.ForgotPasswordModule;

/**
 * Forgot Password Activity class for handling functionality with the Forgot Password screen.
 *
 * @author Luke Lengel
 * @since October 18, 2021
 */
public class ForgotPasswordActivity extends AppCompatActivity {
    private ForgotPasswordModule forgotPasswordModule;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

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
        sendButton = findViewById(R.id.send_button__forgot_password);
    }

    /**
     * Initializes any listeners that are being used in the activity.
     */
    private void initListeners() {
        sendButton.setOnClickListener(v -> forgotPasswordModule.forgotPassword());
    }

    /**
     * Initializes any services being used by the activity.
     */
    private void initServices() {
        forgotPasswordModule = new ForgotPasswordModule(this);
    }
}