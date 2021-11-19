package com.ridge.digitalreceiptreader.activity.login;

import android.os.Bundle;
import android.widget.Button;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.login.module.ForgotPasswordActivityModule;
import com.ridge.digitalreceiptreader.common.abstracts.BaseActivity;

/**
 * Forgot Password Activity class for handling functionality with the Forgot
 * Password screen.
 *
 * @author Luke Lengel
 * @since October 18, 2021
 */
public class ForgotPasswordActivity extends BaseActivity {
    private ForgotPasswordActivityModule forgotPasswordActivityModule;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        initialization();
    }

    /**
     * Initializes any elements that are being used in the activity.
     */
    public void initElements() {
        sendButton = findViewById(R.id.send_button__forgot_password);
    }

    /**
     * Initializes any listeners that are being used in the activity.
     */
    public void initListeners() {
        sendButton.setOnClickListener(v -> forgotPasswordActivityModule.forgotPassword());
    }

    /**
     * Initializes any services being used by the activity.
     */
    public void initServices() {
        forgotPasswordActivityModule = new ForgotPasswordActivityModule(this);
    }
}