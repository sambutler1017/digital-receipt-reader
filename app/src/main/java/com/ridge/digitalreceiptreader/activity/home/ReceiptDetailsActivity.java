package com.ridge.digitalreceiptreader.activity.home;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.home.module.ReceiptDetailsModule;
import com.ridge.digitalreceiptreader.common.abstracts.BaseActivity;
import com.squareup.picasso.Picasso;

/**
 * Receipt details activity that will show more information about a
 * receipt
 *
 * @author Sam Butler
 * @since November 20, 2021
 */
public class ReceiptDetailsActivity extends BaseActivity {
    ReceiptDetailsModule module;
    ImageView receiptImage;
    ImageView closeIcon;
    int imageBaseHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_details);

        initialization();
        displayReceipt(getIntent().getExtras().getInt("receiptId"));
    }

    /**
     * Initialize any elements being used in the activity.
     */
    public void initElements() {
        receiptImage = findViewById(R.id.receiptDetails__receiptImage__imageView);
        imageBaseHeight = receiptImage.getLayoutParams().height;
        closeIcon = findViewById(R.id.receiptDetails__closeIcon__imageView);
    }

    /**
     * Initialize the base listeners for the activity
     */
    public void initListeners() {
        receiptImage.setOnClickListener(v -> module.onImageZoomClick(v, imageBaseHeight));
        closeIcon.setOnClickListener(v -> module.navigateHome());
    }

    /**
     * Initializes any services being used by the activity.
     */
    public void initServices() {
        module = new ReceiptDetailsModule(this);
    }

    public void displayReceipt(int receiptId) {
        module.getReceiptById(receiptId);
    }
}
