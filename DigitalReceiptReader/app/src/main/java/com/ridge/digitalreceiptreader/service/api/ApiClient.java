package com.ridge.digitalreceiptreader.service.api;

import com.ridge.digitalreceiptreader.app.user.domain.User;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

/**
 * Api client to consume restful endpoints and communicate with database
 *
 * @author Sam Butler
 * @since July 31, 2021
 */
public class ApiClient {
    private final String BASE_URL = "https://digital-receipt-api.herokuapp.com/";
    private RestTemplate restTemplate;

    public ApiClient() {
        restTemplate = new RestTemplate();
    }

    /**
     * Gets a single record for a request.
     *
     * @param url The url to consume the endpoint of.
     * @param clazz What class to map the object too.
     * @param <T> Type of object to map.
     * @return Return the associated generic object type with the retrieved data.
     */
    public <T> T get(String url, Class<T> clazz) {
        ApiRequest request = new ApiRequest();

        request.setUrl("https://www.metaweather.com/api/location/search/?query=london");
        request.setMethod(HttpMethod.GET);
        request.setClazz(User[].class);

        try {
           return new ApiTask<T>().execute(request).get().getBody();
        } catch(Exception e) {
            return null;
        }
    }
}
