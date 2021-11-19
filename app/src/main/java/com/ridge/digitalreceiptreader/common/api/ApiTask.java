package com.ridge.digitalreceiptreader.common.api;

import android.os.AsyncTask;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Api task class to do background network api processes in the background.
 * Helps to not bog down the main thread.
 *
 * @param <T> Object type that class should be initialized in.
 * @author Sam Butler
 * @since July 31, 2021
 */
public class ApiTask<T> extends AsyncTask<ApiRequest, Void, ResponseEntity<T>> {

    /**
     * Background method to run api task that are on the main thread.
     *
     * @param params The params to pass with the api endpoint.
     * @return Responses Entity object of the data.
     */
    @Override
    protected ResponseEntity doInBackground(@NotNull ApiRequest... params) {
        ApiRequest request = params[0];
        HttpMethod requestMethod = request.getMethod();

        try {
            if (requestMethod.equals(HttpMethod.GET))
                return getTask(request);
            else if (requestMethod.equals(HttpMethod.POST))
                return postTask(request);
            else if (requestMethod.equals(HttpMethod.PUT))
                return putTask(request);
            else if (requestMethod.equals(HttpMethod.DELETE))
                return deleteTask(request);
            else
                return invalidMethod(requestMethod);
        } catch (HttpServerErrorException errorException) {
            return new ResponseEntity(errorException.getResponseHeaders(), errorException.getStatusCode());
        }
    }

    /**
     * The get task for api request.
     *
     * @param request The request object to consume the endpoint with.
     * @return Responses Entity object of the data.
     */
    private ResponseEntity getTask(@NotNull ApiRequest request) {
        HttpEntity requestEntity = new HttpEntity(request.getHeaders());
        return new RestTemplate().exchange(request.getUrl(), HttpMethod.GET, requestEntity, request.getClazz());
    }

    /**
     * The post task for api request, includes a post body.
     *
     * @param request The request object to consume the endpoint with.
     * @return Responses Entity object of the data.
     */
    private ResponseEntity postTask(@NotNull ApiRequest request) {
        HttpEntity postRequestBody = new HttpEntity(request.getPostParams(), request.getHeaders());
        return new RestTemplate().postForEntity(request.getUrl(), postRequestBody, request.getClazz());
    }

    /**
     * The put task for api request, includes a post body.
     *
     * @param request The request object to consume the endpoint with.
     * @return Responses Entity object of the data.
     */
    private ResponseEntity putTask(@NotNull ApiRequest request) {
        HttpEntity postRequestBody = new HttpEntity(request.getPostParams(), request.getHeaders());
        return new RestTemplate().exchange(request.getUrl(), HttpMethod.PUT, postRequestBody, request.getClazz());
    }

    /**
     * The delete task for api request.
     *
     * @param request The request object to consume the endpoint with.
     * @return Responses Entity object of the data.
     */
    private ResponseEntity deleteTask(@NotNull ApiRequest request) {
        HttpEntity requestEntity = new HttpEntity(request.getHeaders());
        return new RestTemplate().exchange(request.getUrl(), HttpMethod.DELETE, requestEntity, request.getClazz());
    }

    /**
     * Returns bad request when invalid or unsupported method type is passed.
     *
     * @param method The invalid method type.
     * @return Response Entity of type bad request.
     */
    private ResponseEntity invalidMethod(@NotNull HttpMethod method) {
        Log.e("Invalid API Method", method.toString());
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
