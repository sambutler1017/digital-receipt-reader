package com.ridge.digitalreceiptreader.activity.login;

import android.os.Bundle;
import android.widget.Button;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.login.module.CreateAccountActivityModule;
import com.ridge.digitalreceiptreader.common.abstracts.BaseActivity;

/**
 * Service for handling functionality with the CreateUserActivity.
 *
 * @author Luke Lengel
 * @since October 18, 2021
 */
public class CreateAccountActivity extends BaseActivity {
    private CreateAccountActivityModule createAccountActivityModule;
    private Button createAccountButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        initialization();
    }

    /**
     * Initializes any elements that are being used in the activity.
     */
    public void initElements() {
        createAccountButton = findViewById(R.id.create_account_button__create_account);
    }

    /**
     * Initializes any listeners that are being used in the activity.
     */
    public void initListeners() {
        createAccountButton.setOnClickListener(v -> createAccountActivityModule.onCreateAccount());
    }

    /**
     * Initializes any services being used by the activity.
     */
    public void initServices() {
        createAccountActivityModule = new CreateAccountActivityModule(this);
    }
}