package com.ridge.digitalreceiptreader.common.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

/**
 * Api request class that is used to make request to apis.
 *
 * @param <T> Type of class to map api too.
 * @author Sam Butler
 * @since July 31, 2021
 */
public class ApiRequest<T> {
    private String url;

    private HttpMethod method;

    private T postParams;

    private Class<T> clazz;

    private HttpHeaders headers;

    public ApiRequest() {
        url = null;
        method = null;
        clazz = null;
        headers = null;
    }

    public ApiRequest(String url, HttpMethod method, Class<T> clazz, HttpHeaders headers) {
        this.url = url;
        this.method = method;
        this.clazz = clazz;
        this.headers = headers;
    }

    public ApiRequest(String url, HttpMethod method, Class<T> clazz, HttpHeaders headers, T postParams) {
        this.url = url;
        this.method = method;
        this.clazz = clazz;
        this.postParams = postParams;
        this.headers = headers;
    }

    public ApiRequest(String url, HttpMethod method, Class<T> clazz, T postParams) {
        this.url = url;
        this.method = method;
        this.clazz = clazz;
        this.postParams = postParams;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public T getPostParams() {
        return postParams;
    }

    public void setPostParams(T postParams) {
        this.postParams = postParams;
    }
}
