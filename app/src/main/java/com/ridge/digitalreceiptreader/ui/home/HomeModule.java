package com.ridge.digitalreceiptreader.ui.home;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ridge.digitalreceiptreader.common.abstracts.BaseModule;

/**
 * Home Module class to centralize methods being using in Home fragment.
 *
 * @author Sam Butler
 * @since October 30, 2021
 */
public class HomeModule extends BaseModule {

    /**
     * Sets default values for the class.
     *
     * @param v current view.
     */
    public HomeModule(View v) {
        super(v);
    }
}