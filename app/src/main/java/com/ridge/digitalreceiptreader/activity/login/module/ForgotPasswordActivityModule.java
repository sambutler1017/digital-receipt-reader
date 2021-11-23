package com.ridge.digitalreceiptreader.activity.login.module;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.login.ForgotPasswordActivity;
import com.ridge.digitalreceiptreader.common.abstracts.ActivityModule;
import com.ridge.digitalreceiptreader.service.util.ToastService;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

/**
 * Module class to centralize methods being using in the ForgotPasswordActivity.
 *
 * @author Luke Lengel
 * @since October 23, 2021
 */
public class ForgotPasswordActivityModule extends ActivityModule<ForgotPasswordActivity> {
    private ToastService toastService;

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
    }

    /**
     * Initializes any elements that are being used in the activity.
     */
    public void initElements() {
        emailInput = appContext.findViewById(R.id.email_textbox__forgot_password);
        sendButton = appContext.findViewById(R.id.send_button__forgot_password);
        loadingIndicator = appContext.findViewById(R.id.loading_indicator__forgot_password);
        hide(loadingIndicator);
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
        else if (!isValidEmail(email)) {
            toastService.showError("Invalid email address");
        } else {
            // TODO: update endpoint to use the forgot password method in the {@link UserClient}
        }
    }

    /**
     * Validates email address.
     *
     * @see <a href=
     *      "https://stackoverflow.com/questions/1819142/how-should-i-validate-an-e-mail-address">Email
     *      Validation</a>
     * @param target email address
     * @return if email address is valid or not
     */
    private final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}