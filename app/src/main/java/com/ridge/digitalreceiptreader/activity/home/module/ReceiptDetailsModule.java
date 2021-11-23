package com.ridge.digitalreceiptreader.activity.home.module;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.home.MainActivity;
import com.ridge.digitalreceiptreader.activity.home.ReceiptDetailsActivity;
import com.ridge.digitalreceiptreader.app.receipt.client.ReceiptClient;
import com.ridge.digitalreceiptreader.app.receipt.domain.Receipt;
import com.ridge.digitalreceiptreader.common.abstracts.ActivityModule;
import com.ridge.digitalreceiptreader.common.utils.CommonUtils;
import com.ridge.digitalreceiptreader.service.util.RouterService;
import com.ridge.digitalreceiptreader.service.util.ToastService;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

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
    private ToastService toastService;

    private ImageView receiptImage;
    private TextView locationField;
    private TextView labelField;
    private TextView dateField;
    private TextView notesField;
    private TextView notesLabel;
    private TextView detailsLabel;
    private LinearLayout detailsLinearLayout;
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
        notesField = appContext.findViewById(R.id.receiptDetails__notes__textView);
        notesLabel = appContext.findViewById(R.id.receiptDetails__notesLabel__textView);
        detailsLinearLayout = appContext.findViewById(R.id.receiptDetails__detailsCard__linearLayout);
        detailsLabel = appContext.findViewById(R.id.receiptDetails__detailsLabel__textView);
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
        toastService = new ToastService(appContext);
    }

    /**
     * This will get called when the user clicks on the image. This will zoom it in
     * so they can see the image more clearly. It will toggle between the two view
     * everytime they click on the image.
     *
     * @param imageBaseHeight The base height of the image that was originally set.
     */
    public void onImageZoomClick(int imageBaseHeight) {
        ViewGroup.LayoutParams layoutParams = receiptImage.getLayoutParams();
        if (layoutParams.height == ViewGroup.LayoutParams.MATCH_PARENT) {
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
        receiptClient.getUserReceiptById(id)
                .subscribe(res -> appContext.runOnUiThread(() -> populateReceiptFields(res.getBody())));
        hide(loader);
    }

    /**
     * If an error occurs getting the receipt or if the receipt can not be loaded,
     * then it will show a toast message and route the home page.
     */
    public void displayErrorAndNavigateHome() {
        hide(loader);
        toastService.showError("Could not load receipt. Try again later.");
        navigateHome();
    }

    /**
     * Delete the current receipt for the given receipt id. Once the receipt has
     * been deleted it will then route to the home page.
     *
     * @param receiptId The id of the receipt to be deleted.
     */
    public void onDeleteReceipt(int receiptId) {
        TextView title = new TextView(appContext);
        title.setText("Delete Receipt?");
        title.setPadding(20, 30, 20, 30);
        title.setTextSize(20F);
        title.setBackgroundColor(Color.parseColor("#E83345"));
        title.setTextColor(Color.WHITE);

        AlertDialog.Builder alert = new AlertDialog.Builder(appContext);
        alert.setCustomTitle(title);
        alert.setMessage("This will remove the receipt from your account. Once the receipt " +
                         "is deleted you will not have access to it. Do you want to continue?");
        alert.setPositiveButton("Delete", (dialog, i) -> deleteReceipt(receiptId));
        alert.setNegativeButton("Cancel", null);
        alert.setCancelable(false);

        AlertDialog dialog = alert.create();
        dialog.setOnShowListener(
                arg0 -> dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#E83345")));
        dialog.show();
    }

    /**
     * This will show the new intent to edit a receipt information. It will then save
     * the receiptId in order to know where to save the changes too.
     *
     * @param receiptId The id of the receipt.
     */
    public void onEditReceipt(int receiptId) {
        System.out.println("Edit Icon Clicked");
    }

    /**
     * Delete the receipt from the user.m
     *
     * @param receiptId The id of the receipt to be deleted
     */
    private void deleteReceipt(int receiptId) {
        show(loader);
        receiptClient.deleteUserReceipt(receiptId).subscribe(res -> appContext.runOnUiThread(() -> {
            hide(loader);
            toastService.showSuccess("Receipt Successfully Deleted!");
            navigateHome();
        }));
    }

    /**
     * This will populate the page with the receipt details that the user has set.
     *
     * @param receipt The receipt information.
     */
    private void populateReceiptFields(Receipt receipt) {
        Picasso.get().load(receipt.getUrl()).into(receiptImage, new Callback() {
            @Override
            public void onSuccess() {
                locationField.setText(receipt.getLocation());
                labelField.setText(receipt.getLabel());
                dateField.setText(CommonUtils.formatDate(receipt.getInsertDate()));
                notesField.setText(receipt.getNotes());
                showContent();
            }

            @Override
            public void onError(Exception e) {
                displayErrorAndNavigateHome();
            }
        });
    }

    /**
     * After everything is populated, show the data on the page.
     */
    private void showContent() {
        show(receiptImage);
        show(detailsLabel);
        show(notesField);
        show(notesLabel);
        show(detailsLinearLayout);
        delayLoaderHide();
    }

    /**
     * Delays a second for when the loader is hidden again so it gives time for the
     * receipt image to load.
     */
    private void delayLoaderHide() {
        new Handler().postDelayed(() -> hide(loader), 500);
    }
}
