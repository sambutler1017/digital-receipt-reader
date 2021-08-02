package com.ridge.digitalreceiptreader.service.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * Api client to consume restful endpoints and communicate with database
 *
 * @author Sam Butler
 * @since July 31, 2021
 */
public class ApiClient {
    private final String BASE_URL = "https://digital-receipt-api.herokuapp.com";
    private final String LOCAL_URL = "http://10.0.2.2:8080";

    /**
     * Gets data from the given url for the request.
     *
     * @param url The url to consume the endpoint of.
     * @param clazz What class to map the object too.
     * @param <T> Type of object to map.
     * @return Return the associated generic object type with the retrieved data, if error occurs
     * then it will return null object.
     */
    public <T> ResponseEntity<T> get(String url, Class<T> clazz) {
        try {
           return new ApiTask<T>().execute(new ApiRequest(buildUrl(url), HttpMethod.GET, clazz, getHeaders())).get();
        } catch(Exception e) {
            return null;
        }
    }

    /**
     * Gets data from the given url for the request, uses the given override url.
     *
     * @param url The url to consume the endpoint of, without base url.
     * @param clazz What class to map the object too.
     * @param <T> Type of object to map.
     * @return Return the associated generic object type with the retrieved data, if error occurs
     * then it will return null object.
     */
    public <T> ResponseEntity<T> getOverrideUrl(String url, Class<T> clazz) {
        try {
            return new ApiTask<T>().execute(new ApiRequest(buildUrl(url), HttpMethod.GET, clazz, getHeaders())).get();
        } catch(Exception e) {
            return null;
        }
    }

    /**
     * Posts a given object to the provided api endpoint. Will use absolute path.
     *
     * @param url The url to consume the endpoint too.
     * @param paramBody The parambody that should be posted.
     * @param clazz What class the return type should be mapped too.
     * @param <T> Type of class to return.
     * @param <R> Object of the post body.
     * @return The generic type of the mapped object.
     */
    public <T, R> ResponseEntity<T> post(String url, R paramBody, Class<T> clazz) {
        try {
            return new ApiTask<T>().execute(new ApiRequest(buildUrl(url), HttpMethod.POST, clazz, getHeaders(), paramBody)).get();
        } catch(Exception e) {
            return null;
        }
    }

    /**
     * Posts a given object to the provided api endpoint, uses the given override url.
     *
     * @param url The url to consume the endpoint too.
     * @param paramBody The parambody that should be posted.
     * @param clazz What class the return type should be mapped too.
     * @param <T> Type of class to return.
     * @param <R> Object of the post body.
     * @return The generic type of the mapped object.
     */
    public <T, R> ResponseEntity<T> postOverrideUrl(String url, R paramBody, Class<T> clazz) {
        try {
            return new ApiTask<T>().execute(new ApiRequest(url, HttpMethod.POST, clazz, getHeaders(), paramBody)).get();
        } catch(Exception e) {
            return null;
        }
    }

    /**
     * Builds out the url with the qualified endpoint path
     *
     * @param url The url to be appended to the base url path.
     * @return String of the complete url.
     */
    private String buildUrl(String url) {
        return String.format("%s/%s", BASE_URL, url);
    }

    /**
     * Builds out the ApiRequest object to make the rest template request.
     *
     * @param url The url to consume the endpoint of, without base url.
     * @param method Type of method to make the request too.
     * @param clazz What class to map the object too.
     * @param <T> Type of object to map.
     * @return Return the associated generic object type with the retrieved data, if error occurs
     * then it will return null object.
     */
    private <T> ApiRequest<T> buildRequest(String url, HttpMethod method, Class<T> clazz) {
        return new ApiRequest(buildUrl(url), method, clazz, getHeadersWithToken());
    }

    /**
     * Gets the default API headers for the request.
     *
     * @return HttpHeaders object
     */
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    /**
     * Gets the default API headers for the request, along with
     * the authentication token.
     *
     * @return HttpHeaders object
     */
    private HttpHeaders getHeadersWithToken() {
        HttpHeaders headersWithToken = getHeaders();
        headersWithToken.set("Authorization", "Bearer: "+ "");
        return headersWithToken;
    }
}
