package com.ridge.digitalreceiptreader.ui.home;

import android.view.View;

import com.ridge.digitalreceiptreader.common.abstracts.FragmentModule;

/**
 * Home Module class to centralize methods being using in Home fragment.
 *
 * @author Sam Butler
 * @since October 30, 2021
 */
public class HomeFragmentModule extends FragmentModule<HomeFragment> {

    /**
     * Sets default values for the class.
     *
     * @param f current fragment.
     */
    public HomeFragmentModule(HomeFragment f, View v) {
        super(f, v);
    }
}