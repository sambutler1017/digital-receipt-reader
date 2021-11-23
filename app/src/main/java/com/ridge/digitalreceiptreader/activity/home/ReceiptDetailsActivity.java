package com.ridge.digitalreceiptreader.activity.home;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.home.module.ReceiptDetailsModule;
import com.ridge.digitalreceiptreader.common.abstracts.BaseActivity;

import android.os.Bundle;
import android.widget.ImageView;

/**
 * Receipt details activity that will show more information about a receipt
 *
 * @author Sam Butler
 * @since November 20, 2021
 */
public class ReceiptDetailsActivity extends BaseActivity {
    private ReceiptDetailsModule module;
    private ImageView receiptImage;
    private ImageView closeIcon;
    private ImageView deleteIcon;

    int imageBaseHeight;
    int currentReceiptId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_details);

        initialization();

        if (getIntent().getExtras() == null) {
            module.displayErrorAndNavigateHome();
        } else {
            currentReceiptId = getIntent().getExtras().getInt("receiptId");
            displayReceipt(currentReceiptId);
        }
    }

    /**
     * Initialize any elements being used in the activity.
     */
    public void initElements() {
        receiptImage = findViewById(R.id.receiptDetails__receiptImage__imageView);
        imageBaseHeight = receiptImage.getLayoutParams().height;
        closeIcon = findViewById(R.id.receiptDetails__closeIcon__imageView);
        deleteIcon = findViewById(R.id.receiptDetails__trashIcon__imageView);
    }

    /**
     * Initialize the base listeners for the activity
     */
    public void initListeners() {
        receiptImage.setOnClickListener(v -> module.onImageZoomClick(imageBaseHeight));
        closeIcon.setOnClickListener(v -> module.navigateHome());
        deleteIcon.setOnClickListener(v -> module.onDeleteReceipt(currentReceiptId));
    }

    /**
     * Initializes any services being used by the activity.
     */
    public void initServices() {
        module = new ReceiptDetailsModule(this);
    }

    /**
     * Get the receipt for the given receipt id.
     *
     * @param receiptId The id of the receipt to get.
     */
    public void displayReceipt(int receiptId) {
        module.getReceiptById(receiptId);
    }
}
