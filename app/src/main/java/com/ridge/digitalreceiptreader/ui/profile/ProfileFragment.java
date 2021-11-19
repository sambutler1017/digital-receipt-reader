package com.ridge.digitalreceiptreader.ui.profile;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.common.abstracts.BaseFragment;

/**
 * Profile fragment used to display the profile page in the main activity.
 *
 * @author Sam Butler
 * @since October 30, 2021
 */
public class ProfileFragment extends BaseFragment {
    private ProfileFragmentModule profileFragmentModule;

    private TextView currentPassword;
    private TextView newPassword;
    private Button saveButton;

    /**
     * This will create the view for the fragment from the given layout and the view
     * group.
     *
     * @param i  The layout to place the view.
     * @param c  The group the view is contained in
     * @param sI The bundle to use.
     * @return {@link View} data
     */
    public View onCreateView(@NonNull LayoutInflater i, ViewGroup c, Bundle sI) {
        initialization(i, c, R.layout.fragment_profile);
        return view;
    }

    /**
     * Initializes any elements that are being used in the activity.
     */
    public void initElements() {
        currentPassword = view.findViewById(R.id.profile__currentPassword__textView);
        currentPassword.setTransformationMethod(new PasswordTransformationMethod());
        newPassword = view.findViewById(R.id.profile__newPassword__textView);
        newPassword.setTransformationMethod(new PasswordTransformationMethod());
        saveButton = view.findViewById(R.id.profile__save__button);
    }

    /**
     * Initializes any listeners that are being used in the activity.
     */
    public void initListeners() {
        saveButton.setOnClickListener(v -> profileFragmentModule.saveProfile());
    }

    /**
     * Initializes any services being used by the activity.
     */
    public void initServices() {
        profileFragmentModule = new ProfileFragmentModule(this, view);
    }
}
