package com.ridge.digitalreceiptreader.service.login;

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
import com.ridge.digitalreceiptreader.service.util.ToastService;

/**
 * Service class to centralize methods being using in the CreateAccountActivity.
 *
 * @author Luke Lengel
 * @since October 23, 2021
 */
public class CreateAccountService {
    private final Activity currentActivity;
    private ToastService toastService;

    private AuthClient authClient;

    private EditText nameInput;
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
    public CreateAccountService(Activity a) {
        currentActivity = a;

        initElements();
        initServices();
        initClients();
    }

    /**
     * Initializes any clients to be used for api calls.
     */
    private void initClients() {
        authClient = new AuthClient(currentActivity);
    }

    /**
     * Initializes any service classes being used in the activity.
     */
    private void initServices() {
        toastService = new ToastService(currentActivity);
    }

    /**
     * Initializes any elements that are being used in the activity.
     */
    private void initElements() {
        nameInput = currentActivity.findViewById(R.id.name_textbox__create_account);
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
        showLoading();

        // Get user input
        String name = nameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String confirmPassword = confirmPasswordInput.getText().toString().trim();



        // Checks if fields are populated
        if (name.length()            == 0 ||
            email.length()           == 0 ||
            password.length()        == 0 ||
            confirmPassword.length() == 0) {
            toastService.showError("Please fill in all fields");
        }
        // Validates email field
        else if (!isValidEmail(email)) {
            toastService.showError("Invalid email address");
        }
        // Validates password fields
        else if(!password.equals(confirmPassword)) {
            toastService.showError("Passwords do not match");
        }
        // Writes account info to database and logs user in
        else {
            // TODO: Write account info to backend
            Intent intent = new Intent(currentActivity, MainActivity.class);
            currentActivity.startActivity(intent);
        }
        hideLoading();
    }

    /**
     * Hides the create account button and shows the loading indicator.
     */
    private void showLoading() {
        loadingIndicator.setVisibility(View.VISIBLE);
        createAccountButton.setVisibility(View.GONE);
    }

    /**
     * Shows the create account button and hides the loading indicator.
     */
    private void hideLoading() {
        loadingIndicator.setVisibility(View.GONE);
        createAccountButton.setVisibility(View.VISIBLE);
    }

    /**
     * Validates email address.
     * NOTE: Taken from
     *       https://stackoverflow.com/questions/1819142/how-should-i-validate-an-e-mail-address
     *
     * @param target email address
     * @return if email address is valid or not
     */
    private final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
