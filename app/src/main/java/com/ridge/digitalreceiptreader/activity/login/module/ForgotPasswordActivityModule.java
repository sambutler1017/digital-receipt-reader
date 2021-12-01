package com.ridge.digitalreceiptreader.activity.login.module;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.login.ForgotPasswordActivity;
import com.ridge.digitalreceiptreader.activity.login.LoginActivity;
import com.ridge.digitalreceiptreader.app.user.client.UserClient;
import com.ridge.digitalreceiptreader.common.abstracts.ActivityModule;
import com.ridge.digitalreceiptreader.common.utils.CommonUtils;
import com.ridge.digitalreceiptreader.service.util.RouterService;
import com.ridge.digitalreceiptreader.service.util.ToastService;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.springframework.http.ResponseEntity;

import io.reactivex.rxjava3.core.Single;

/**
 * Module class to centralize methods being using in the ForgotPasswordActivity.
 *
 * @author Luke Lengel
 * @since October 23, 2021
 */
public class ForgotPasswordActivityModule extends ActivityModule<ForgotPasswordActivity> {
    private ToastService toastService;
    private RouterService router;
    private UserClient userClient;

    private EditText emailInput;
    private Button sendButton;
    private ProgressBar loadingIndicator;

    /**
     * Base constructor to setting up the activity module.
     *
     * @param a The activity to pull and modify data from.
     */
    public ForgotPasswordActivityModule(ForgotPasswordActivity a) {
        super(a);
    }

    /**
     * Initializes any service classes being used in the activity.
     */
    public void initServices() {
        toastService = new ToastService(appContext);
        router = new RouterService(appContext);
    }

    /**
     * Initializes any client classes being used in the activity.
     */
    public void initClients() {
        userClient = new UserClient(appContext);
    }

    /**
     * Initializes any elements that are being used in the activity.
     */
    public void initElements() {
        emailInput = appContext.findViewById(R.id.email_textbox__forgot_password);
        sendButton = appContext.findViewById(R.id.send_button__forgot_password);
        loadingIndicator = appContext.findViewById(R.id.loading_indicator__forgot_password);
    }

    /**
     * This method will handle the email validation. If all email validation passes
     * then it will send a forgot email message to that email so that the user can
     * recover their account.
     */
    public void forgotPassword() {
        // Get user input
        String email = emailInput.getText().toString().trim();

        if (email.length() == 0) {
            toastService.showError("Please fill in email field");
        }
        // Validates email field
        else if (!CommonUtils.isValidEmail(email)) {
            toastService.showError("Invalid email address");
        } else {
            show(loadingIndicator);
            hide(sendButton);
            userClient.doesEmailExist(email)
                    .filter(status -> checkEmail(status.getBody()))
                    .flatMap(res -> userClient.forgotPassword(email).toMaybe())
                    .subscribe(res -> appContext.runOnUiThread(() -> routeToLogin()));
        }
    }

    /**
     * Checks to see if the email exists or not.
     *
     * @param status The status of the email.
     * @return {@link Boolean} if the observable should continue.
     */
    public boolean checkEmail(Boolean status) {
        if(status) {
            return true;
        } else {
            toastService.showError("Email does not exist!");
            return false;
        }
    }

    /**
     * Route to the login page and show toast message of status of email.
     */
    private void routeToLogin() {
        toastService.showSuccess("Email has been successfully sent! Follow instructions in email.");
        router.navigate(LoginActivity.class);
    }
}