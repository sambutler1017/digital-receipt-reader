package com.ridge.digitalreceiptreader.activity.login.module;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.login.LoginActivity;
import com.ridge.digitalreceiptreader.activity.login.ResetPasswordActivity;
import com.ridge.digitalreceiptreader.app.user.client.UserClient;
import com.ridge.digitalreceiptreader.common.abstracts.ActivityModule;
import com.ridge.digitalreceiptreader.service.util.RouterService;
import com.ridge.digitalreceiptreader.service.util.ToastService;

/**
 * Module for resetting a users password.
 *
 * @author Sam Butler
 * @since December 1, 2021
 */
public class ResetPasswordActivityModule extends ActivityModule<ResetPasswordActivity> {
    private ToastService toastService;
    private RouterService router;
    private UserClient userClient;

    private EditText passwordInput;
    private EditText confirmPasswordInput;
    private Button resetPasswordButton;
    private ProgressBar loadingIndicator;

    /**
     * Sets default values for the class.
     *
     * @param a current activity.
     */
    public ResetPasswordActivityModule(ResetPasswordActivity a) {
        super(a);
    }

    /**
     * Initializes any clients to be used for api calls.
     */
    public void initClients() {
        userClient = new UserClient(appContext);
    }

    /**
     * Initializes any service classes being used in the activity.
     */
    public void initServices() {
        toastService = new ToastService(appContext);
        router = new RouterService(appContext);
    }

    /**
     * Initializes any elements that are being used in the activity.
     */
    public void initElements() {
        passwordInput = appContext.findViewById(R.id.password_textbox__reset_password);
        confirmPasswordInput = appContext.findViewById(R.id.confirm_password_textbox__reset_password);
        resetPasswordButton = appContext.findViewById(R.id.reset_password_button__reset_password);
        loadingIndicator = appContext.findViewById(R.id.loading_indicator__reset_password);
    }

    /**
     * When the user clicks to reset their password.
     */
    public void onResetPassword(String token) {
        show(loadingIndicator);
        hide(resetPasswordButton);

        String password = passwordInput.getText().toString().trim();
        String confirmPassword = confirmPasswordInput.getText().toString().trim();

        if (password.length() == 0 || confirmPassword.length() == 0) {
            toastService.showError("Please fill in all fields");
        }
        else if (!password.equals(confirmPassword)) {
            toastService.showError("Passwords do not match");
        } else {
            userClient.resetUserPassword(password, token).subscribe(res -> appContext.runOnUiThread(() -> routeToLoginSuccess()));
        }
    }

    /**
     * Route to the login page when the user's password has been changed.
     */
    private void routeToLoginSuccess() {
        toastService.showSuccess("Password has successfully been reset!");
        router.navigate(LoginActivity.class);
    }
}
