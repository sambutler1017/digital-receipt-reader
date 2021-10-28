package com.ridge.digitalreceiptreader.activity.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.login.module.CreateAccountModule;

/**
 * Service for handling functionality with the CreateUserActivity.
 *
 * @author Luke Lengel
 * @since October 18, 2021
 */
public class CreateAccountActivity extends AppCompatActivity {
    private CreateAccountModule createAccountModule;
    private Button createAccountButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

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
        createAccountButton = findViewById(R.id.create_account_button__create_account);
    }

    /**
     * Initializes any listeners that are being used in the activity.
     */
    private void initListeners() {
        createAccountButton.setOnClickListener(v -> createAccountModule.onCreateAccount());
    }

    /**
     * Initializes any services being used by the activity.
     */
    private void initServices() {
        createAccountModule = new CreateAccountModule(this);
    }
}