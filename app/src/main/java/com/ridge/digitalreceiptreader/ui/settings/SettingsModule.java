package com.ridge.digitalreceiptreader.ui.settings;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ridge.digitalreceiptreader.common.abstracts.BaseModule;

/**
 * Settings Module class to centralize methods being using in Settings fragment.
 *
 * @author Sam Butler
 * @since October 30, 2021
 */
public class SettingsModule extends BaseModule {

    /**
     * Sets default values for the class.
     *
     * @param v current view.
     */
    public SettingsModule(View v) {
        super(v);
    }
}