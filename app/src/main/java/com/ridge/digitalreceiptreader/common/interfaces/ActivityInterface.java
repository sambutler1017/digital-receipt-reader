package com.ridge.digitalreceiptreader.common.interfaces;

/**
 * Fragment interface to know what methods that class would need to
 * implement.
 *
 * @author Sam Butler
 * @since October 30, 2021
 */
public interface ActivityInterface {
    /**
     * Initializes any elements that are being used in the activity.
     */
    void initElements();

    /**
     * Initializes any listeners that are being used in the activity.
     */
    void initListeners();

    /**
     * Initializes any services being used by the activity.
     */
    void initServices();
}
