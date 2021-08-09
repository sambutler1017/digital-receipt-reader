package com.ridge.digitalreceiptreader.common.enums;

/**
 * Web Role enum to map user and admin roles.
 *
 * @author Sam Butler
 * @since July 31, 2021
 */
public enum WebRole {
    ADMIN(1), USER(2);

    private int id;

    WebRole(int type) {
        this.id = type;
    }

    public int id() {
        return id;
    }

    public static WebRole getRole(int id) {
        for (WebRole w : WebRole.values())
            if (w.id == id)
                return w;
        return USER;
    }

    public int getValue() {
        return id;
    }
}
