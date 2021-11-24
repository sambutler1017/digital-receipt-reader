package com.ridge.digitalreceiptreader.activity.home;

import android.os.Bundle;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.home.module.ReceiptDetailsEditModule;
import com.ridge.digitalreceiptreader.common.abstracts.BaseActivity;

/**
 * Activity for managing and display the receipt details edit
 * page.
 *
 * @author Sam Butler
 * @since November 24, 2021
 */
public class ReceiptDetailsEditActivity extends BaseActivity {

    private ReceiptDetailsEditModule module;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_details_edit);

        initialization();
    }

    /**
     * Initialize any elements being used in the activity.
     */
    public void initElements() {
    }

    /**
     * Initialize the base listeners for the activity
     */
    public void initListeners() {
    }

    /**
     * Initializes any services being used by the activity.
     */
    public void initServices() {
        module = new ReceiptDetailsEditModule(this);
    }
}
