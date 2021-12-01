package com.ridge.digitalreceiptreader.ui.profile;

import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.common.abstracts.FragmentModule;
import com.ridge.digitalreceiptreader.service.util.ToastService;

/**
 * Profile Module class to centralize methods being using in login activity.
 *
 * @author Sam Butler
 * @since October 29, 2021
 */
public class ProfileFragmentModule extends FragmentModule<ProfileFragment> {
    private ToastService toastService;

    private TextView currentPassword;
    private TextView newPassword;
    private Button saveButton;

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
    }

    /**
     * Initializes any elements that are being used in the activity.
     */
    public void initElements() {
        currentPassword = view.findViewById(R.id.createAccount__password__editText);
        currentPassword.setTransformationMethod(new PasswordTransformationMethod());
        newPassword = view.findViewById(R.id.createAccount__confirmPassword__editText);
        newPassword.setTransformationMethod(new PasswordTransformationMethod());
        saveButton = view.findViewById(R.id.createAccount__createAccount__button);
    }

    public void saveProfile() {
    }
}
