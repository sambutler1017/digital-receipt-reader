package com.ridge.digitalreceiptreader.activity.util;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.util.module.StartModule;
import com.ridge.digitalreceiptreader.common.abstracts.BaseActivity;

/**
 * Splash screen for when the app first starts up.
 *
 * @author Sam Butler
 * @since October 18, 2021
 */
public class StartActivity extends BaseActivity {
    private StartModule startModule;

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
        startModule = new StartModule(this);
    }
}
