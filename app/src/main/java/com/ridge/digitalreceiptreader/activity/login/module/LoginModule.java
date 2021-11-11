package com.ridge.digitalreceiptreader.activity.login.module;

import android.app.Activity;
import android.content.Intent;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.ridge.digitalreceiptreader.activity.login.CreateAccountActivity;
import com.ridge.digitalreceiptreader.activity.login.ForgotPasswordActivity;
import com.ridge.digitalreceiptreader.activity.home.MainActivity;
import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.app.auth.client.AuthClient;
import com.ridge.digitalreceiptreader.app.auth.domain.DigitalReceiptToken;
import com.ridge.digitalreceiptreader.common.abstracts.BaseModule;
import com.ridge.digitalreceiptreader.service.jwt.JwtHolder;
import com.ridge.digitalreceiptreader.service.util.LocalStorageService;
import com.ridge.digitalreceiptreader.service.util.RouterService;
import com.ridge.digitalreceiptreader.service.util.ToastService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Login Module class to centralize methods being using in login activity.
 *
 * @author Sam Butler & Luke Lengel
 * @since October 23, 2021
 */
public class LoginModule extends BaseModule {
    private ToastService toastService;
    private LocalStorageService localStorage;
    private JwtHolder jwtHolder;
    private RouterService router;

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
    public LoginModule(Activity a) {
        super(a);
        if (jwtHolder.hasToken()) {
            router.navigateHome();
        }
    }

    /**
     * Initializes any clients to be used for api calls.
     */
    public void initClients() {
        authClient = new AuthClient(activity);
    }

    /**
     * Initializes any service classes being used in the activity.
     */
    public void initServices() {
        localStorage = new LocalStorageService(activity);
        toastService = new ToastService(activity);
        jwtHolder = new JwtHolder(activity);
        router = new RouterService(activity);
    }

    /**
     * Initializes any elements that are being used in the activity.
     */
    public void initElements() {
        emailInput = activity.findViewById(R.id.email_textbox__login);
        passwordInput = activity.findViewById(R.id.password_textbox__login);
        passwordInput.setTransformationMethod(new PasswordTransformationMethod());
        loginButton = activity.findViewById(R.id.login_button__login);
        loadingIndicator = activity.findViewById(R.id.loading_indicator__login);
        loadingIndicator.setVisibility(View.GONE);
    }

    /**
     * Login click event for when the user clicks the login button.
     */
    public void onLogin() {
        String email = emailInput.getText().toString().trim().equals("") ? " " : emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim().equals("") ? " " : passwordInput.getText().toString().trim();

        show(loadingIndicator);
        hide(loginButton);
        authClient.authenticate(email, password).subscribe(res -> activity.runOnUiThread(() -> validateToken(res)));
    }

    /**
     * Method for handling when the create user link is clicked from login page.
     */
    public void onCreateUser() {
        router.navigate(CreateAccountActivity.class);
    }

    /**
     * Method for handling when the forgot password link is clicked (from the login page).
     */
    public void onForgotPassword() {
        router.navigate(ForgotPasswordActivity.class);
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
            router.navigateHome();
            localStorage.setToken(authToken.getBody().getToken());
        } else {
            toastService.showError("Invalid Credentials!");
        }
        hide(loadingIndicator);
        show(loginButton);
    }
}
