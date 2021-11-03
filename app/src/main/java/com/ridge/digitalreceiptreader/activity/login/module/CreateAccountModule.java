package com.ridge.digitalreceiptreader.activity.login.module;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.ridge.digitalreceiptreader.activity.home.MainActivity;
import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.app.auth.client.AuthClient;
import com.ridge.digitalreceiptreader.app.user.client.UserClient;
import com.ridge.digitalreceiptreader.app.user.domain.User;
import com.ridge.digitalreceiptreader.common.abstracts.BaseModule;
import com.ridge.digitalreceiptreader.common.utils.CommonUtils;
import com.ridge.digitalreceiptreader.service.util.ToastService;

/**
 * Module class to centralize methods being using in the CreateAccountActivity.
 *
 * @author Luke Lengel
 * @since October 23, 2021
 */
public class CreateAccountModule extends BaseModule {
    private ToastService toastService;

    private AuthClient authClient;
    private UserClient userClient;

    private EditText firstNameInput;
    private EditText lastNameInput;
    private EditText emailInput;
    private EditText passwordInput;
    private EditText confirmPasswordInput;
    private Button createAccountButton;
    private ProgressBar loadingIndicator;

    /**
     * Sets default values for the class.
     *
     * @param a current activity.
     */
    public CreateAccountModule(Activity a) {
        super(a);
    }

    /**
     * Initializes any clients to be used for api calls.
     */
    public void initClients() {
        authClient = new AuthClient(currentActivity);
        userClient = new UserClient(currentActivity);
    }

    /**
     * Initializes any service classes being used in the activity.
     */
    public void initServices() {
        toastService = new ToastService(currentActivity);
    }

    /**
     * Initializes any elements that are being used in the activity.
     */
    public void initElements() {
        firstNameInput = currentActivity.findViewById(R.id.first_name_textbox__create_account);
        lastNameInput = currentActivity.findViewById(R.id.last_name_textbox__create_account);
        emailInput = currentActivity.findViewById(R.id.email_textbox__create_account);
        passwordInput = currentActivity.findViewById(R.id.password_textbox__create_account);
        confirmPasswordInput = currentActivity.findViewById(R.id.confirm_password_textbox__create_account);
        createAccountButton = currentActivity.findViewById(R.id.create_account_button__create_account);
        loadingIndicator = currentActivity.findViewById(R.id.loading_indicator__create_account);
        loadingIndicator.setVisibility(View.GONE);
    }

    /**
     * Method for handling when the user creates their account.
     */
    public void onCreateAccount() {
        show(loadingIndicator);
        hide(createAccountButton);

        // Get user input
        String firstName = firstNameInput.getText().toString().trim();
        String lastName = lastNameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String confirmPassword = confirmPasswordInput.getText().toString().trim();



        // Checks if fields are populated
        if (firstName.length()       == 0 ||
            lastName.length()        == 0 ||
            email.length()           == 0 ||
            password.length()        == 0 ||
            confirmPassword.length() == 0) {
            toastService.showError("Please fill in all fields");
        }
        // Validates email field
        else if (!CommonUtils.isValidEmail(email)) {
            toastService.showError("Invalid email address");
        }
        // Validates password fields
        else if(!password.equals(confirmPassword)) {
            toastService.showError("Passwords do not match");
        }
        // Writes account info to database and logs user in
        else {
            userClient.createUser(new User(firstName,lastName,email,password));
            Intent intent = new Intent(currentActivity, MainActivity.class);
            currentActivity.startActivity(intent);
        }
        hide(loadingIndicator);
        show(createAccountButton);
    }
}
