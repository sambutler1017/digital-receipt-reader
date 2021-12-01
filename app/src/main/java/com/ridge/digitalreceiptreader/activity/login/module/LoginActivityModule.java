package com.ridge.digitalreceiptreader.activity.login.module;

import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.login.CreateAccountActivity;
import com.ridge.digitalreceiptreader.activity.login.ForgotPasswordActivity;
import com.ridge.digitalreceiptreader.activity.login.LoginActivity;
import com.ridge.digitalreceiptreader.app.auth.client.AuthClient;
import com.ridge.digitalreceiptreader.app.auth.domain.DigitalReceiptToken;
import com.ridge.digitalreceiptreader.common.abstracts.ActivityModule;
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
public class LoginActivityModule extends ActivityModule<LoginActivity> {
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
    public LoginActivityModule(LoginActivity a) {
        super(a);
        if (jwtHolder.hasToken()) {
            router.navigateHome();
        }
    }

    /**
     * Initializes any clients to be used for api calls.
     */
    public void initClients() {
        authClient = new AuthClient(appContext);
    }

    /**
     * Initializes any service classes being used in the activity.
     */
    public void initServices() {
        localStorage = new LocalStorageService(appContext);
        toastService = new ToastService(appContext);
        jwtHolder = new JwtHolder(appContext);
        router = new RouterService(appContext);
    }

    /**
     * Initializes any elements that are being used in the activity.
     */
    public void initElements() {
        emailInput = appContext.findViewById(R.id.email_textbox__login);
        passwordInput = appContext.findViewById(R.id.password_textbox__login);
        passwordInput.setTransformationMethod(new PasswordTransformationMethod());
        loginButton = appContext.findViewById(R.id.login_button__login);
        loadingIndicator = appContext.findViewById(R.id.loading_indicator__login);
        hide(loadingIndicator);
    }

    /**
     * Login click event for when the user clicks the login button.
     */
    public void onLogin() {
        show(loadingIndicator);
        hide(loginButton);
        authClient.authenticate(emailInput.getText().toString().trim(), passwordInput.getText().toString().trim()).subscribe(res -> appContext.runOnUiThread(() -> validateToken(res)));
    }

    /**
     * Method for handling when the create user link is clicked from login page.
     */
    public void onCreateUser() {
        router.navigate(CreateAccountActivity.class);
    }

    /**
     * Method for handling when the forgot password link is clicked (from the login
     * page).
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
