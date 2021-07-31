package com.ridge.digitalreceiptreader.app.user.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("title")
    private String title;
    @JsonProperty("location_type")
    private String location_type;
    @JsonProperty("woeid")
    private int woeid;
    @JsonProperty("latt_long")
    private String latt_long;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation_type() {
        return location_type;
    }

    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }

    public int getWoeid() {
        return woeid;
    }

    public void setWoeid(int woeid) {
        this.woeid = woeid;
    }

    public String getLatt_long() {
        return latt_long;
    }

    public void setLatt_long(String latt_long) {
        this.latt_long = latt_long;
    }
}
