package com.ridge.digitalreceiptreader.ui.profile;

import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.common.abstracts.BaseModule;
import com.ridge.digitalreceiptreader.service.util.ToastService;

/**
 * Profile Module class to centralize methods being using in login activity.
 *
 * @author Sam Butler
 * @since October 29, 2021
 */
public class ProfileModule extends BaseModule {
    private ToastService toastService;

    private TextView currentPassword;
    private TextView newPassword;
    private Button saveButton;

    /**
     * Sets default values for the class.
     *
     * @param v current view.
     */
    public ProfileModule(View v) {
        super(v);
    }

    /**
     * Initializes any service classes being used in the activity.
     */
    public void initServices() {
        toastService = new ToastService(currentView.getContext());
    }

    /**
     * Initializes any elements that are being used in the activity.
     */
    public void initElements() {
        currentPassword = currentView.findViewById(R.id.profile__currentPassword__textView);
        currentPassword.setTransformationMethod(new PasswordTransformationMethod());
        newPassword = currentView.findViewById(R.id.profile__newPassword__textView);
        newPassword.setTransformationMethod(new PasswordTransformationMethod());
        saveButton = currentView.findViewById(R.id.profile__save__button);
    }

    public void saveProfile() {
        System.out.println(currentPassword.getText());
    }
}
