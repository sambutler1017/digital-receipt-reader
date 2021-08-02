package com.ridge.digitalreceiptreader.service.login;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.ridge.digitalreceiptreader.MainActivity;
import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.app.auth.client.AuthClient;
import com.ridge.digitalreceiptreader.app.auth.domain.DigitalReceiptToken;
import com.ridge.digitalreceiptreader.service.toast.ToastService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Login service class to centralize methods being using in login activity.
 *
 * @author Sam Butler
 * @since July 28, 2021
 */
public class LoginService {
    private Activity currentActivity;
    private ToastService toastService;
    private AuthClient authClient;

    private EditText emailInput;
    private EditText passwordInput;
    private Button loginButton;
    private ProgressBar loadingIndicator;

    /**
     * Sets default values for the class.
     *
     * @param a current activity.
     */
    public LoginService(Activity a) {
        currentActivity = a;

        initElements();
        initServices();
        initClients();
    }

    /**
     * Initializes any clients to be used for api calls.
     */
    private void initClients() {
        authClient = new AuthClient();
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

        emailInput = (EditText) currentActivity.findViewById(R.id.email_textbox__login);
        passwordInput = (EditText) currentActivity.findViewById(R.id.password_textbox__login);
        loginButton = (Button) currentActivity.findViewById(R.id.login_button__login);
        loadingIndicator = (ProgressBar) currentActivity.findViewById(R.id.loading_indicator__login);
        loadingIndicator.setVisibility(View.GONE);
    }

    /**
     * Login click event for when the user clicks the login button.
     */
    public void onLogin() {
        showLoading();
        authClient.authenticate(emailInput.getText().toString(), passwordInput.getText().toString())
                .subscribe(res -> currentActivity.runOnUiThread(() -> validateToken(res)));
    }

    /**
     * Validates the given response entity auth token. If it is valid then the user
     * will be routed to the home page, otherwise will see a red toast message of
     * invalid credentials.
     *
     * @param authToken The auth token to validate.
     */
    private void validateToken(ResponseEntity<DigitalReceiptToken> authToken) {
        if (authToken.getStatusCode().equals(HttpStatus.OK)) {
            toastService.showSuccess("Logged in Successfully!");
            Intent intent = new Intent(currentActivity, MainActivity.class);
            currentActivity.startActivity(intent);
        } else {
            toastService.showError("Invalid Credentials!");
            hideLoading();
        }
    }

    /**
     * Hides the login button and shows the loading indicator.
     */
    private void showLoading() {
        loadingIndicator.setVisibility(View.VISIBLE);
        loginButton.setVisibility(View.GONE);
    }

    /**
     * Shows the login buttons and hides the loading indicator.
     */
    private void hideLoading() {
        loadingIndicator.setVisibility(View.GONE);
        loginButton.setVisibility(View.VISIBLE);
    }
}
