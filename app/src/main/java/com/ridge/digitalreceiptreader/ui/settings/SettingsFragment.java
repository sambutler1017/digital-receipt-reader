package com.ridge.digitalreceiptreader.ui.settings;

import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.common.abstracts.BaseFragment;
import com.ridge.digitalreceiptreader.ui.nfc.NFCModule;

/**
 * Settings fragment used to display the Settings page in the main
 * activity.
 *
 * @author Sam Butler
 * @since October 30, 2021
 */
public class SettingsFragment extends BaseFragment {
    private SettingsModule settingsModule;
    private TextView logOut;

    /**
     * This will create the view for the fragment from the given layout and the
     * view group.
     *
     * @param i The layout to place the view.
     * @param c The group the view is contained in
     * @param sI The bundle to use.
     * @return {@link View} data
     */
    public View onCreateView(@NonNull LayoutInflater i, ViewGroup c, Bundle sI) {
        initialization(i, c, R.layout.fragment_settings);
        return view;
    }

    /**
     * Initializes any elements being used by the activity.
     */
    public void initElements() {
        logOut = view.findViewById(R.id.settings__logOutButton__label);
    }

    /**
     * Initializes any listeners being used by the activity.
     */
    public void initListeners() {
        logOut.setOnClickListener(v -> settingsModule.onLogOutClick());
    }

    /**
     * Initializes any services being used by the activity.
     */
    public void initServices() {
        settingsModule = new SettingsModule(view);
    }
}