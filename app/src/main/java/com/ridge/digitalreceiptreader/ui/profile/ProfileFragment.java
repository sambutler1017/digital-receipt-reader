package com.ridge.digitalreceiptreader.ui.profile;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ridge.digitalreceiptreader.R;

public class ProfileFragment extends Fragment {
    private ProfileModule profileModule;

    private TextView currentPassword;
    private TextView newPassword;
    private Button saveButton;

    private View profileView;

    public View onCreateView(@NonNull LayoutInflater i, ViewGroup c, Bundle sI) {
        initialization(i, c);
        return profileView;
    }

    /**
     * Starting initialization of the activity.
     */
    private void initialization(LayoutInflater i, ViewGroup c) {
        profileView = i.inflate(R.layout.fragment_profile, c, false);

        initElements();
        initListeners();
        initServices();
    }

    /**
     * Initializes any elements that are being used in the activity.
     */
    private void initElements() {
        currentPassword = profileView.findViewById(R.id.profile__currentPassword__textView);
        currentPassword.setTransformationMethod(new PasswordTransformationMethod());
        newPassword = profileView.findViewById(R.id.profile__newPassword__textView);
        newPassword.setTransformationMethod(new PasswordTransformationMethod());
        saveButton = profileView.findViewById(R.id.profile__save__button);
    }

    /**
     * Initializes any listeners that are being used in the activity.
     */
    private void initListeners() {
        saveButton.setOnClickListener(v -> profileModule.saveProfile());
    }

    /**
     * Initializes any services being used by the activity.
     */
    private void initServices() {
        profileModule = new ProfileModule(profileView);
    }
}
