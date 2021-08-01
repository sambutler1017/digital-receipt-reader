package com.ridge.digitalreceiptreader.service.api;

import android.os.AsyncTask;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Api task class to do background network api processes in the background. Helps
 * to not bog down the main thread.
 *
 * @param <T> Object type that class should be initialized in.
 * @author Sam Butler
 * @since July 31, 2021
 */
public class ApiTask<T> extends AsyncTask<ApiRequest, ResponseEntity<T>, ResponseEntity<T>> {

    /**
     * Background method to run api task that are on the main thread.
     *
     * @param params The params to pass with the api endpoint.
     * @return Responses Entity object of the data.
     */
    @Override
    protected ResponseEntity<T> doInBackground(ApiRequest... params) {
        ApiRequest request = params[0];

        try {
            if(request.getMethod().equals(HttpMethod.GET)) return getTask(request);
            else return postTask(request);
        } catch (HttpServerErrorException errorException) {
            return new ResponseEntity<T>(errorException.getResponseHeaders(), errorException.getStatusCode());
        }
    }

    /**
     * The get task for api request.
     *
     * @param request The request object to consume the endpoint with.
     * @return Responses Entity object of the data.
     */
    private ResponseEntity<T> getTask(ApiRequest request) {
        return new RestTemplate().getForEntity(request.getUrl(), request.getClazz());
    }

    /**
     * The post task for api request, includes a post body.
     *
     * @param request The request object to consume the endpoint with.
     * @return Responses Entity object of the data.
     */
    private ResponseEntity<T> postTask(ApiRequest request) {
        HttpEntity postRequestBody = new HttpEntity(request.getPostParams(), request.getHeaders());
        return new RestTemplate().postForEntity(request.getUrl(), postRequestBody, request.getClazz());
    }
}
