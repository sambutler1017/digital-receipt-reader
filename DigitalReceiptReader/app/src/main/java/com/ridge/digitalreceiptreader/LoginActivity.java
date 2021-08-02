package com.ridge.digitalreceiptreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.ridge.digitalreceiptreader.app.auth.client.AuthClient;
import com.ridge.digitalreceiptreader.app.auth.domain.DigitalReceiptToken;
import com.ridge.digitalreceiptreader.service.toast.ToastService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Login Activity class for handling functionality with the login screen.
 *
 * @author Sam Butler
 * @since July 28, 2021
 */
public class LoginActivity extends AppCompatActivity {
    private ToastService toastService;
    private AuthClient authClient;

    private EditText emailInput;
    private EditText passwordInput;
    private Button loginButton;
    private ProgressBar loadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initClients();
        initServices();
        initElements();
        initListeners();
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
        toastService = new ToastService(this);
    }

    /**
     * Initializes any elements that are being used in the activity.
     */
    private void initElements() {
        emailInput = (EditText) findViewById(R.id.email_textbox__login);
        passwordInput = (EditText) findViewById(R.id.password_textbox__login);
        loginButton = (Button) findViewById(R.id.login_button__login);
        loadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator__login);
        loadingIndicator.setVisibility(View.GONE);
    }

    /**
     * Initializes any listeners that are being used in the activity.
     */
    private void initListeners() {
        loginButton.setOnClickListener(v -> onLogin(v));
    }

    /**
     * Login click event for when the user clicks the login button.
     *
     * @param view Current view of the activity.
     */
    public void onLogin(View view) {
        showLoading();
        authClient.authenticate(emailInput.getText().toString(), passwordInput.getText().toString())
                .subscribe(res -> runOnUiThread(() -> validateToken(res)));
    }

    /**
     * Validates the given response entity auth token. If it is valid then the user
     * will be routed to the home page, otherwise will see a red toast message of
     * invalid credentials.
     *
     * @param authToken The authtoken to validate.
     */
    private void validateToken(ResponseEntity<DigitalReceiptToken> authToken) {
        if (authToken.getStatusCode().equals(HttpStatus.OK)) {
            toastService.showSuccess("Logged in Successfully!");
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
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