package com.ridge.digitalreceiptreader.service.api;

import org.springframework.http.HttpMethod;

public class ApiRequest<T> {
    private String url;

    private HttpMethod method;

    private Class<T> clazz;

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
}
