package com.ridge.digitalreceiptreader.service.api;

import android.os.AsyncTask;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ApiTask<T> extends AsyncTask<ApiRequest, ResponseEntity<T>, ResponseEntity<T>> {
    @Override
    protected ResponseEntity<T> doInBackground(ApiRequest... params) {
        ApiRequest request = params[0];

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> response = restTemplate.getForEntity(request.getUrl(), request.getClazz());
        return response;
    }
}
