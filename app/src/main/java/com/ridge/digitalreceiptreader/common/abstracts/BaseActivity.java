package com.ridge.digitalreceiptreader.common.abstracts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.common.interfaces.ActivityInterface;

import java.util.List;

/**
 * Base activity class to implement common methods to be used.
 *
 * @author Sam Butler
 * @since October 29, 2021
 */
public abstract class BaseActivity extends AppCompatActivity implements ActivityInterface {
    /**
     * Base initialization of the activity
     */
    public void initialization() {
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

    /**
     * This will get the current displaying fragment in the activity. If the
     * activity does not contain any fragments then this will retunr null.
     *
     * @return {@link Fragment} of the one being displayed.
     */
    public Fragment getVisibleFragment() {
        List<Fragment> fragments = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)
                .getChildFragmentManager().getFragments();
        return fragments.isEmpty() ? null : fragments.get(0);
    }
}
