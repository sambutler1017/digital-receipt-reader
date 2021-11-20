package com.ridge.digitalreceiptreader.activity.home.module;

import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.home.ReceiptDetailsActivity;
import com.ridge.digitalreceiptreader.common.abstracts.ActivityModule;

/**
 * Receipts details module for doing all the base functionality for processing
 * information.
 *
 * @author Sam Butler
 * @since November 20, 2021
 */
public class ReceiptDetailsModule extends ActivityModule<ReceiptDetailsActivity> {
    ImageView receiptImage;

    /**
     * Sets default values for the class.
     *
     * @param a current activity.
     */
    public ReceiptDetailsModule(ReceiptDetailsActivity a) {
        super(a);
    }

    /**
     * Initializes any elements that are being used in the activity.
     */
    public void initElements() {
        receiptImage = appContext.findViewById(R.id.receiptDetails__receiptImage__imageView);
    }

    /**
     * This will get called when the user clicks on the image. This will zoom it in so they
     * can see the image more clearly. It will toggle between the two view everytime they
     * click on the image.
     *
     * @param v The current view when the image was clicked.
     * @param imageBaseHeight The base height of the image that was originally set.
     */
    public void onImageZoomClick(View v, int imageBaseHeight) {
        ViewGroup.LayoutParams layoutParams = v.findViewById(R.id.receiptDetails__receiptImage__imageView).getLayoutParams();
        if(layoutParams.height == ViewGroup.LayoutParams.MATCH_PARENT) {
            layoutParams.height = imageBaseHeight;
        } else {
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        }
        receiptImage.setLayoutParams(layoutParams);
    }
}
