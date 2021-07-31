package com.ridge.digitalreceiptreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.ridge.digitalreceiptreader.app.user.domain.User;
import com.ridge.digitalreceiptreader.service.api.ApiClient;
import com.ridge.digitalreceiptreader.service.api.ApiRequest;
import com.ridge.digitalreceiptreader.service.api.ApiTask;
import com.ridge.digitalreceiptreader.service.toast.ToastService;

import org.json.JSONArray;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.GenericArrayType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Login Activity class for handling functionality with the login screen.
 *
 * @author Sam Butler
 * @since July 28, 2021
 */
public class LoginActivity extends AppCompatActivity {
    private ToastService toastService;

    private EditText emailInput;
    private EditText passwordInput;
    private Button loginButton;
    private ProgressBar loadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initServices();
        initElements();
        initListeners();
    }

    /**
     * Initializes any service classes being used in the activity.
     */
    private void initServices() {
        toastService = new ToastService(this);
    }

    /**
     * Initializes any elements that are being used in the activity.
     */
    private void initElements() {
        emailInput = (EditText) findViewById(R.id.email_textbox__login);
        passwordInput = (EditText) findViewById(R.id.password_textbox__login);
        loginButton = (Button) findViewById(R.id.login_button__login);
        loadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator__login);
        loadingIndicator.setVisibility(View.GONE);
    }

    /**
     * Initializes any listeners that are being used in the activity.
     */
    private void initListeners() {
        loginButton.setOnClickListener(v -> onLogin(v));
    }

    /**
     * Login click event for when the user clicks the login button.
     *
     * @param view Current view of the activity.
     */
    public void onLogin(View view) {
        restTemplateTest();
        if (emailInput.getText().toString().equals("test") && passwordInput.getText().toString().equals("password")) {
            loadingIndicator.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.GONE);

            Handler handler = new Handler();
            handler.postDelayed(() -> {
                toastService.showSuccess("Logged in Successfully!");
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                loadingIndicator.setVisibility(View.GONE);
            }, 2000);

        } else {
            toastService.showError("Invalid Credentials!");
        }
    }

    public void restTemplateTest() {
        ApiClient clientApi = new ApiClient();
        loginButton
                .setText(clientApi.get("https://www.metaweather.com/api/location/search/?query=london", User[].class)[0]
                        .getTitle());
    }
}