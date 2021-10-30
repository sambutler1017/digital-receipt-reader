package com.ridge.digitalreceiptreader.common.abstracts;

import android.os.Bundle;
import android.text.Layout;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.common.interfaces.ActivityInterface;
import com.ridge.digitalreceiptreader.common.interfaces.ModuleInterface;
import com.ridge.digitalreceiptreader.ui.profile.ProfileModule;

import org.xmlpull.v1.XmlPullParser;

/**
 * Base fragment class to implement common methods to be used.
 *
 * @author Sam Butler
 * @since October 29, 2021
 */
public abstract class BaseFragment extends Fragment implements ActivityInterface {
    protected View view;

    /**
     * Base initialization of the activity
     *
     * @param i The layout to place the view.
     * @param c The group the view is contained in
     * @param l The layout to perform the operations on.
     */
    public void initialization(LayoutInflater i, ViewGroup c, int l) {
        view = i.inflate(l, c, false);

        initElements();
        initListeners();
        initServices();
    }

    /**
     * Empty initialization in case child class does not implement method.
     */
    public void initElements() {
    }

    /**
     * Empty initialization in case child class does not implement method.
     */
    public void initListeners() {
    }

    /**
     * Empty initialization in case child class does not implement method.
     */
    public void initServices() {
    }
}
