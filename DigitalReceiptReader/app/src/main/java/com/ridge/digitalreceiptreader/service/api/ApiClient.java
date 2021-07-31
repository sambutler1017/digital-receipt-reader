package com.ridge.digitalreceiptreader.service.api;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class ApiClient {

    private static RequestQueue requestQueue;

    public ApiClient(Context c) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(c);
        }
    }

}
