package com.ridge.digitalreceiptreader.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.common.abstracts.BaseFragment;

/**
 * Home fragment used to display the home page in the main
 * activity.
 *
 * @author Sam Butler
 * @since October 30, 2021
 */
public class HomeFragment extends BaseFragment {
    private HomeFragmentModule homeFragmentModule;

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
        initialization(i, c, R.layout.fragment_home);
        return view;
    }

    /**
     * Initializes any services being used by the activity.
     */
    public void initServices() {
        homeFragmentModule = new HomeFragmentModule(this, view);
    }
}