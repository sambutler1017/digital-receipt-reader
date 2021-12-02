package com.ridge.digitalreceiptreader.ui.profile;

import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.app.auth.client.AuthClient;
import com.ridge.digitalreceiptreader.app.auth.domain.DigitalReceiptToken;
import com.ridge.digitalreceiptreader.app.user.client.UserClient;
import com.ridge.digitalreceiptreader.app.user.domain.PasswordUpdate;
import com.ridge.digitalreceiptreader.app.user.domain.User;
import com.ridge.digitalreceiptreader.common.abstracts.FragmentModule;
import com.ridge.digitalreceiptreader.common.utils.CommonUtils;
import com.ridge.digitalreceiptreader.service.jwt.JwtHolder;
import com.ridge.digitalreceiptreader.service.util.LocalStorageService;
import com.ridge.digitalreceiptreader.service.util.ToastService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.reactivex.rxjava3.core.Single;

/**
 * Profile Module class to centralize methods being using in login activity.
 *
 * @author Sam Butler
 * @since October 29, 2021
 */
public class ProfileFragmentModule extends FragmentModule<ProfileFragment> {
    private ToastService toastService;
    private JwtHolder jwtHolder;
    private LocalStorageService localStorage;

    private UserClient userClient;
    private AuthClient authClient;

    private EditText currentPasswordInput;
    private EditText newPasswordInput;
    private EditText firstNameInput;
    private EditText lastNameInput;
    private EditText emailInput;
    private Button saveButton;
    private ProgressBar loadingIndicatorSave;

    /**
     * Sets default values for the class.
     *
     * @param f current fragment.
     */
    public ProfileFragmentModule(ProfileFragment f, View v) {
        super(f, v);
    }

    /**
     * Initializes any service classes being used in the activity.
     */
    public void initServices() {
        toastService = new ToastService(activity);
        localStorage = new LocalStorageService(activity);
        jwtHolder = new JwtHolder(activity);
    }

    /**
     * Initializes any clients classes being used in the activity.
     */
    public void initClients() {
        userClient = new UserClient(activity);
        authClient = new AuthClient(activity);
    }

    /**
     * Initializes any elements that are being used in the activity.
     */
    public void initElements() {
        firstNameInput = view.findViewById(R.id.profile__firstName__editText);
        lastNameInput = view.findViewById(R.id.profile__lastName__editText);
        emailInput = view.findViewById(R.id.profile__email__editText);
        currentPasswordInput = view.findViewById(R.id.profile__currentPassword__editText);
        currentPasswordInput.setTransformationMethod(new PasswordTransformationMethod());
        newPasswordInput = view.findViewById(R.id.profile__newPassword__editText);
        newPasswordInput.setTransformationMethod(new PasswordTransformationMethod());
        saveButton = view.findViewById(R.id.profile__save__button);
        loadingIndicatorSave = view.findViewById(R.id.loading_indicator_save__profile);
    }

    /**
     * Fill in the form with the user's current information.
     */
    public void fillInForm() {
        firstNameInput.setText(jwtHolder.get("firstName"));
        lastNameInput.setText(jwtHolder.get("lastName"));
        emailInput.setText(jwtHolder.get("email"));
    }

    /**
     * This will save the profile of the current input fields. It will first check to see
     * that all input fields are valid.
     */
    public void saveProfile() {
        Single<ResponseEntity<User>> obs = getObservableData();

        if(obs != null) {
            show(loadingIndicatorSave);
            hide(saveButton);
            obs.filter(res -> {
                boolean status = determineAuthStatus(res);
                if(!status) {
                    activity.runOnUiThread(() -> showErrorPasswords());
                }
                return status;
            }).flatMap(res -> authClient.reauthenticate().toMaybe())
                    .subscribe(res -> activity.runOnUiThread(() -> displayResult(res)));
        } else {
            toastService.showWarning("No Changes to input fields!");
        }
    }

    /**
     * This will get the observable data needed to be executed.
     *
     * @return {@link Single} to be run
     */
    public Single<ResponseEntity<User>> getObservableData() {
        String firstName = firstNameInput.getText().toString().trim();
        String lastName = lastNameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String password = newPasswordInput.getText().toString().trim();
        String currentPassword = currentPasswordInput.getText().toString().trim();

        // Both profile and password fields are valid
        if(validateProfileInputs() && validatePasswordInputs()) {
            return userClient.updateUser(new User(firstName, lastName, email))
                    .flatMap(res -> userClient.updateUserPassword(new PasswordUpdate(currentPassword, password)));
        }

        // Online profile input fields are valid
        if(validateProfileInputs() && !validatePasswordInputs()) {
            return userClient.updateUser(new User(firstName, lastName, email));
        }

        // Only password input fields are valid
        if(!validateProfileInputs() && validatePasswordInputs()) {
            return userClient.updateUserPassword(new PasswordUpdate(currentPassword, password));
        }

        // None of the fields are valid
        return null;
    }

    /**
     * This will check to make sure that all input fields that are needed are
     * correct and not blank.
     *
     * @return {@link Boolean} if the fields are valid
     */
    private boolean validateProfileInputs() {
        String firstName = firstNameInput.getText().toString().trim();
        String lastName = lastNameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();

        // User has not changes these fields
        if(firstName.equals(jwtHolder.get("firstName")) && lastName.equals(jwtHolder.get("lastName")) && email.equals(jwtHolder.get("email"))) {
            return false;
        }

        if(firstName.length() == 0 || lastName.length() == 0 || email.length() == 0) {
            toastService.showError("Make sure all profile fields are filled out!");
            return false;
        } else if(!CommonUtils.isValidEmail(email)) {
            toastService.showError("Invalid Email!");
            return false;
        } else {
            return true;
        }
    }

    /**
     * This will see if the password fields are valid and that they can be displayed.
     * If these fields are blank then it will return false.
     *
     * @return {@link Boolean} of the status of the passwords.
     */
    private boolean validatePasswordInputs() {
        String password = newPasswordInput.getText().toString().trim();
        String currentPassword = currentPasswordInput.getText().toString().trim();

        if(password.length() == 0 || currentPassword.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * This will show an error toast message that the current password was incorrect
     * and it will hide the loader and show the save button again.
     */
    private void showErrorPasswords() {
        hide(loadingIndicatorSave);
        show(saveButton);
        newPasswordInput.setText("");
        currentPasswordInput.setText("");
        toastService.showError("Current Password is incorrect!");
    }

    /**
     * Display the result of the update.
     */
    private void displayResult(ResponseEntity<DigitalReceiptToken> authToken) {
        hide(loadingIndicatorSave);
        show(saveButton);
        toastService.showSuccess("User Account updated Successfully!");
        localStorage.setToken(authToken.getBody().getToken());
        newPasswordInput.setText("");
        currentPasswordInput.setText("");
    }

    /**
     * This will check to see if it needs to authenticate the user. The only time it wouldn't is
     * when it throws an error.
     *
     * @param userData The response with the user object.
     * @return {@link Boolean} of the ability to not filter.
     */
    private boolean determineAuthStatus(ResponseEntity<User> userData) {
        if(userData.getStatusCode().equals(HttpStatus.OK)) {
            return true;
        } else {
            return false;
        }
    }
}
