package com.ridge.digitalreceiptreader.activity.util;

import android.os.Bundle;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.util.module.StartActivityModule;
import com.ridge.digitalreceiptreader.common.abstracts.BaseActivity;

/**
 * Splash screen for when the app first starts up.
 *
 * @author Sam Butler
 * @since October 18, 2021
 */
public class StartActivity extends BaseActivity {
    private StartActivityModule startActivityModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        initialization();
    }

    /**
     * Initializes any services being used by the activity.
     */
    public void initServices() {
        startActivityModule = new StartActivityModule(this);
    }
}
