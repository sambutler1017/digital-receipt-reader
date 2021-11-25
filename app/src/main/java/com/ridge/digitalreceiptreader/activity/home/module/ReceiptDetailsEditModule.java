package com.ridge.digitalreceiptreader.activity.home.module;

import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.home.MainActivity;
import com.ridge.digitalreceiptreader.activity.home.ReceiptDetailsActivity;
import com.ridge.digitalreceiptreader.activity.home.ReceiptDetailsEditActivity;
import com.ridge.digitalreceiptreader.app.receipt.client.ReceiptClient;
import com.ridge.digitalreceiptreader.app.receipt.domain.Receipt;
import com.ridge.digitalreceiptreader.common.abstracts.ActivityModule;
import com.ridge.digitalreceiptreader.service.util.RouterService;
import com.ridge.digitalreceiptreader.service.util.ToastService;

/**
 * Module class for handling the behind the scenes work of the receipt details
 * edit page.
 *
 * @author Sam Butler
 * @since November 24
 */
public class ReceiptDetailsEditModule extends ActivityModule<ReceiptDetailsEditActivity> {
    private ReceiptClient receiptClient;
    private RouterService router;
    private ToastService toastService;

    private TextView locationHeader;
    private EditText locationField;
    private TextView labelHeader;
    private EditText labelField;
    private TextView notesHeader;
    private EditText notesField;
    private Button updateReceiptButton;
    private ProgressBar loader;

    /**
     * Sets default values for the class.
     *
     * @param a current activity.
     */
    public ReceiptDetailsEditModule(ReceiptDetailsEditActivity a) {
        super(a);
    }

    /**
     * Initializes any elements that are being used in the activity.
     */
    public void initElements() {
        locationHeader = appContext.findViewById(R.id.receiptEdit__locationHeader__textView);
        locationField = appContext.findViewById(R.id.receiptEdit__locationField__editText);
        labelHeader = appContext.findViewById(R.id.receiptEdit__labelHeader__textView);
        labelField = appContext.findViewById(R.id.receiptEdit__labelField__editText);
        notesHeader = appContext.findViewById(R.id.receiptEdit__notesHeader__textView);
        notesField = appContext.findViewById(R.id.receiptEdit__notesField__editText);
        loader = appContext.findViewById(R.id.receiptEdit__loader__progressbar);
        updateReceiptButton = appContext.findViewById(R.id.receiptEdit__updateReceipt__button);
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
     * Get the receipt for the given id. Then it will display it on the screen.
     *
     * @param id The id of the receipt to get.
     */
    public void getReceiptById(int id) {
        hideContent();
        show(loader);
        receiptClient.getUserReceiptById(id)
                .subscribe(res -> appContext.runOnUiThread(() -> populateReceiptFields(res.getBody())));
    }

    /**
     * Save the updated receipt information, then navigate to the receipt details
     * page.
     *
     * @param id The id of the receipt to be updated.
     */
    public void updateReceipt(int id) {
        show(loader);
        receiptClient
                .updateUserReceipt(new Receipt(id, locationField.getText().toString().trim(),
                        labelField.getText().toString().trim(), notesField.getText().toString().trim()))
                .subscribe(res -> appContext.runOnUiThread(() -> receiptSuccessfullySaved(id)));
    }

    /**
     * Show success message and then navigate back to the receipt details page.
     *
     * @param id The id of the receipt to be passed to the receipt details page.
     */
    public void receiptSuccessfullySaved(int id) {
        toastService.showSuccess("Receipt successfully Updated!");
        navigateToReceiptDetails(id);
    }

    /**
     * Navigate back to the receipt details page.
     *
     * @param id The id of the receipt to be passed to the receipt details page.
     */
    public void navigateToReceiptDetails(int id) {
        router.navigate(ReceiptDetailsActivity.class, "receiptId", id);
    }

    /**
     * Route the the home page.
     */
    public void navigateHome() {
        router.navigate(MainActivity.class);
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
     * This will populate the page with the receipt details that the user has set.
     *
     * @param receipt The receipt information.
     */
    private void populateReceiptFields(Receipt receipt) {
        locationField.setText(receipt.getLocation());
        labelField.setText(receipt.getLabel());
        notesField.setText(receipt.getNotes());
        showContent();
    }

    /**
     * Hide the content on the page
     */
    private void hideContent() {
        hide(labelHeader);
        hide(labelField);
        hide(locationHeader);
        hide(locationField);
        hide(notesHeader);
        hide(notesField);
        hide(updateReceiptButton);
        delayLoaderHide();
    }

    /**
     * After everything is populated, show the data on the page.
     */
    private void showContent() {
        show(labelHeader);
        show(labelField);
        show(locationHeader);
        show(locationField);
        show(notesHeader);
        show(notesField);
        show(updateReceiptButton);
        delayLoaderHide();
    }

    /**
     * Delays a second for when the loader is hidden again so it gives time for the
     * receipt image to load.
     */
    private void delayLoaderHide() {
        new Handler().postDelayed(() -> hide(loader), 1000);
    }
}
