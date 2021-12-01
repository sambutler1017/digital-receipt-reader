package com.ridge.digitalreceiptreader.activity.login;

import android.os.Bundle;
import android.widget.Button;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.login.module.ResetPasswordActivityModule;
import com.ridge.digitalreceiptreader.common.abstracts.BaseActivity;

/**
 * Activity used to reset a users password.
 *
 * @author Sam Butler
 * @since December 1, 2021
 */
public class ResetPasswordActivity extends BaseActivity {
    private Button resetPasswordButton;
    private ResetPasswordActivityModule module;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        token = getIntent().getData().getLastPathSegment();

        initialization();
    }

    /**
     * Initializes any elements that are being used in the activity.
     */
    public void initElements() {
        resetPasswordButton = findViewById(R.id.reset_password_button__reset_password);
    }

    /**
     * Initializes any clients to be used for api calls.
     */
    public void initListeners() {
        resetPasswordButton.setOnClickListener(v -> module.onResetPassword(token));
    }

    /**
     * Initializes any service classes being used in the activity.
     */
    public void initServices() {
        module = new ResetPasswordActivityModule(this);
    }
}
