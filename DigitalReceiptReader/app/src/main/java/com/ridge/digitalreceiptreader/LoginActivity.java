package com.ridge.digitalreceiptreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ridge.digitalreceiptreader.service.toast.ToastService;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

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
        requestTestMethod();
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

    public void requestTestMethod() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://marcs-microservice.herokuapp.com/api/store-app/stores";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("That didn't work!");
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=UTF-8");
                params.put("Authorization",
                        "Bearer: eyJhbGciOiJIUzUxMiJ9.eyJ3ZWJSb2xlIjoiUkVHSU9OQUwiLCJmaXJzdE5hbWUiOiJCb2IiLCJsYXN0TmFtZSI6IkJ1dGxlciIsImFjY2VzcyI6eyJtYW5hZ2VyIjpbeyJkZXRhaWwiOiJjcnVkIn0seyJ2YWNhdGlvbiI6ImNydWQifV0sImdsb2JhbCI6W3sicHJvZmlsZSI6ImNydWQifSx7Im5vdGlmaWNhdGlvbiI6ImNydWQifV0sInN0b3JlIjpbeyJkZXRhaWwiOiJyIn1dfSwicmVnaW9uIjoiRUFTVCIsImV4cCI6MTYyNzcxMjY0MCwidXNlcklkIjoyLCJpYXQiOjE2Mjc2OTQ2NDAsImVtYWlsIjoidGdidGdAbXNuLmNvbSIsInVzZXJuYW1lIjoiYm9iYnV0bGVyMTAwNiIsImFwcHMiOlsibWFuYWdlciIsInN0b3JlIiwiY2FsZW5kYXIiLCJibG9ja0RhdGVzIiwibWFwIiwicmVwb3J0IiwicmVxdWVzdFRyYWNrZXIiLCJjb250YWN0Il19.hIGSkBphnYfcLzcYkbqbNSvidEdpRzWr9GJfO7D9qu4f5Q5N0X44EDeMZCz9YJNQ7cIXjr3fr8W_OLI6YHAGlA");
                return params;
            }

            ;

        };
        queue.add(request);
    }
}