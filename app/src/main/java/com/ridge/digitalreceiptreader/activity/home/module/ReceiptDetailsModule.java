package com.ridge.digitalreceiptreader.activity.home.module;

import android.os.Handler;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.home.MainActivity;
import com.ridge.digitalreceiptreader.activity.home.ReceiptDetailsActivity;
import com.ridge.digitalreceiptreader.app.receipt.client.ReceiptClient;
import com.ridge.digitalreceiptreader.app.receipt.domain.Receipt;
import com.ridge.digitalreceiptreader.common.abstracts.ActivityModule;
import com.ridge.digitalreceiptreader.service.util.RouterService;
import com.squareup.picasso.Picasso;

import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;

/**
 * Receipts details module for doing all the base functionality for processing
 * information.
 *
 * @author Sam Butler
 * @since November 20, 2021
 */
public class ReceiptDetailsModule extends ActivityModule<ReceiptDetailsActivity> {

    private ReceiptClient receiptClient;
    private RouterService router;

    private ImageView receiptImage;
    private TextView locationField;
    private TextView labelField;
    private TextView dateField;
    private ProgressBar loader;

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
        locationField = appContext.findViewById(R.id.location__field__textView);
        labelField = appContext.findViewById(R.id.label__field__textView);
        dateField = appContext.findViewById(R.id.insertDate__field__textView);
        loader = appContext.findViewById(R.id.receiptDetails__loader__progressbar);
    }

    /**
     * Initialization for clients.
     */
    public void initClients() {
        receiptClient = new ReceiptClient(appContext);
    }

    /**
     * Initialization for the services.
     */
    public void initServices() {
        router = new RouterService(appContext);
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
        ViewGroup.LayoutParams layoutParams = receiptImage.getLayoutParams();
        if(layoutParams.height == ViewGroup.LayoutParams.MATCH_PARENT) {
            layoutParams.height = imageBaseHeight;
        } else {
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        }
        receiptImage.setLayoutParams(layoutParams);
    }

    /**
     * Route the the home page.
     */
    public void navigateHome() {
        router.navigate(MainActivity.class);
    }

    /**
     * Get the receipt for the given id. Then it will display it on the screen.
     *
     * @param id The id of the receipt to get.
     */
    public void getReceiptById(int id) {
        show(loader);
        receiptClient.getUserReceiptById(id).subscribe(res -> appContext.runOnUiThread(() -> populateReceiptFields(res.getBody())));
    }

    /**
     * This will populate the page with the receipt details that the user has set.
     *
     * @param receipt The receipt information.
     */
    public void populateReceiptFields(Receipt receipt) {
        Picasso.get().load(receipt.getUrl()).into(receiptImage);
        locationField.setText(receipt.getLocation());
        labelField.setText(receipt.getLabel());
        dateField.setText(new SimpleDateFormat("MMMM dd, yyyy").format(receipt.getInsertDate()));
        delayLoaderHide();
    }

    /**
     * Delays a second for when the loader is hidden again so it gives time for the receipt
     * image to load.
     */
    public void delayLoaderHide() {
        new Handler().postDelayed(() -> hide(loader), 500);
    }
}
