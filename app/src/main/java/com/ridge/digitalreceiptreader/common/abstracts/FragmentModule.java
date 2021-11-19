package com.ridge.digitalreceiptreader.common.abstracts;

import android.app.Activity;
import android.view.View;

import androidx.fragment.app.Fragment;

/**
 * Common Fragment module class to extend from to use common methods across the
 * application
 *
 * @author Sam Butler
 * @since November 13, 2021
 */
public class FragmentModule<T extends Fragment> extends BaseModule<T> {
    protected View view;
    protected Activity activity;

    /**
     * Base constructor for setting the appView to be the fragment being used.
     *
     * @param fragment The fragment to set the current appView too.
     * @param v        The view of the fragment since all fragments have one.
     */
    public FragmentModule(T fragment, View v) {
        super(fragment);
        view = v;
        activity = appContext.getActivity();
        init();
    }
}
