package com.ridge.digitalreceiptreader.activity.home;

import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

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
    private Button updateReceiptButton;
    private ImageView backArrowIcon;
    int currentReceiptId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_details_edit);

        initialization();

        if (getIntent().getExtras() == null) {
            module.displayErrorAndNavigateHome();
        } else {
            currentReceiptId = getIntent().getExtras().getInt("receiptId");
            module.getReceiptById(currentReceiptId);
        }
    }

    /**
     * Initialize any elements being used in the activity.
     */
    public void initElements() {
        updateReceiptButton = findViewById(R.id.receiptEdit__updateReceipt__button);
        backArrowIcon = findViewById(R.id.receiptEdit__backArrow__imageView);
    }

    /**
     * Initialize the base listeners for the activity
     */
    public void initListeners() {
        updateReceiptButton.setOnClickListener(v -> module.updateReceipt(currentReceiptId));
        backArrowIcon.setOnClickListener(v -> module.navigateToReceiptDetails(currentReceiptId));
    }

    /**
     * Initializes any services being used by the activity.
     */
    public void initServices() {
        module = new ReceiptDetailsEditModule(this);
    }
}
