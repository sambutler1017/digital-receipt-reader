package com.ridge.digitalreceiptreader.ui.nfc;

import android.app.Activity;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ridge.digitalreceiptreader.common.abstracts.BaseModule;

/**
 * NFC Module class to centralize methods being using in NFC fragment.
 *
 * @author Sam Butler
 * @since October 30, 2021
 */
public class NFCModule extends BaseModule {

    /**
     * Sets default values for the class.
     *
     * @param v current view.
     */
    public NFCModule(View v, Activity a) {
        super(v,a);
    }
}