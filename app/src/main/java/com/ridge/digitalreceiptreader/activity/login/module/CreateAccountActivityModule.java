package com.ridge.digitalreceiptreader.activity.login.module;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.home.MainActivity;
import com.ridge.digitalreceiptreader.activity.login.CreateAccountActivity;
import com.ridge.digitalreceiptreader.app.auth.client.AuthClient;
import com.ridge.digitalreceiptreader.app.auth.domain.DigitalReceiptToken;
import com.ridge.digitalreceiptreader.app.user.client.UserClient;
import com.ridge.digitalreceiptreader.app.user.domain.User;
import com.ridge.digitalreceiptreader.common.abstracts.ActivityModule;
import com.ridge.digitalreceiptreader.common.utils.CommonUtils;
import com.ridge.digitalreceiptreader.service.util.LocalStorageService;
import com.ridge.digitalreceiptreader.service.util.RouterService;
import com.ridge.digitalreceiptreader.service.util.ToastService;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Module class to centralize methods being using in the CreateAccountActivity.
 *
 * @author Luke Lengel
 * @since October 23, 2021
 */
public class CreateAccountActivityModule extends ActivityModule<CreateAccountActivity> {
    private ToastService toastService;
    private RouterService router;
    private LocalStorageService localStorage;

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
    public CreateAccountActivityModule(CreateAccountActivity a) {
        super(a);
    }

    /**
     * Initializes any clients to be used for api calls.
     */
    public void initClients() {
        authClient = new AuthClient(appContext);
        userClient = new UserClient(appContext);
    }

    /**
     * Initializes any service classes being used in the activity.
     */
    public void initServices() {
        toastService = new ToastService(appContext);
        router = new RouterService(appContext);
        localStorage = new LocalStorageService(appContext);
    }

    /**
     * Initializes any elements that are being used in the activity.
     */
    public void initElements() {
        firstNameInput = appContext.findViewById(R.id.profile__firstName__editText);
        lastNameInput = appContext.findViewById(R.id.createAccount__lastName__editText);
        emailInput = appContext.findViewById(R.id.createAccount__email__editText);
        passwordInput = appContext.findViewById(R.id.createAccount__password__editText);
        confirmPasswordInput = appContext.findViewById(R.id.createAccount__confirmPassword__editText);
        createAccountButton = appContext.findViewById(R.id.createAccount__createAccount__button);
        loadingIndicator = appContext.findViewById(R.id.loading_indicator__create_account);
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

        if(validFields()) {
            User newUser = new User(firstName, lastName, email, password);
            userClient.createUser(newUser)
                    .flatMap(res -> authClient.authenticate(email, password))
                    .subscribe(res -> appContext.runOnUiThread(() -> routeToHome(res)));
        }
    }

    public void routeToHome(ResponseEntity<DigitalReceiptToken> authToken) {
        if (authToken.getStatusCode().equals(HttpStatus.OK)) {
            toastService.showSuccess("Account Created Successfully!");
            router.navigateHome();
            localStorage.setToken(authToken.getBody().getToken());
        } else {
            toastService.showError("Could not create Account! Try again later.");
        }

        hide(loadingIndicator);
        show(createAccountButton);
    }

    private boolean validFields() {
        // Get user input
        String firstName = firstNameInput.getText().toString().trim();
        String lastName = lastNameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String confirmPassword = confirmPasswordInput.getText().toString().trim();

        if (firstName.length() == 0 || lastName.length() == 0 || email.length() == 0 || password.length() == 0 || confirmPassword.length() == 0) {
            toastService.showError("Please fill in all fields");
            return false;
        }
        // Validates email field
        else if (!CommonUtils.isValidEmail(email)) {
            toastService.showError("Invalid email address");
            return false;
        }
        // Validates password fields
        else if (!password.equals(confirmPassword)) {
            toastService.showError("Passwords do not match");
            return false;
        }

        return true;
    }
}
