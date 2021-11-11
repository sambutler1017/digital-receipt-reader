package com.ridge.digitalreceiptreader.activity.util.module;

import android.app.Activity;
import android.os.Handler;

import com.ridge.digitalreceiptreader.common.abstracts.BaseModule;
import com.ridge.digitalreceiptreader.service.jwt.JwtHolder;
import com.ridge.digitalreceiptreader.service.util.RouterService;

/**
 * Start Module class to centralize methods being using in start activity.
 *
 * @author Sam Butler & Luke Lengel
 * @since October 23, 2021
 */
public class StartModule extends BaseModule  {
    private JwtHolder jwtHolder;
    private RouterService router;

    /**
     * Sets default values for the class.
     *
     * @param a current activity.
     */
    public StartModule(Activity a) {
        super(a);
        delayRoute();
    }

    /**
     * Initializes any service classes being used in the activity.
     */
    public void initServices() {
        jwtHolder = new JwtHolder(activity);
        router = new RouterService(activity);
    }

    /**
     * This is just used to let the application start and buffer before routing to the
     * login or home page.
     */
    public void delayRoute() {
        new Handler().postDelayed(() -> {
            if(jwtHolder.hasToken()) {
                router.navigateHome();
            } else {
                router.navigateLogin();
            }
        }, 1000);
    }
}
