package com.ridge.digitalreceiptreader.service.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Local storage service to access data stored for user preferences.
 *
 * @author Sam Butler
 * @since August 23, 2021
 */
public class LocalStorageService {

    private final Activity currentActivity;

    /**
     * Sets default values for the class.
     *
     * @param a current activity.
     */
    public LocalStorageService(Activity a) {
        currentActivity = a;
    }

    /**
     * Sets the given {@link String} value with the given key in the preferences.
     *
     * @param key   The key to store in the value under.
     * @param value The value to be stored.
     */
    public void setString(String key, String value) {
        getEditor().putString(key, value).commit();
    }

    /**
     * Gets the {@link String} value stored for the given key preference.
     *
     * @param key the key to search for in preferences
     * @return {@link String} stored at the key.
     */
    public String getString(String key) {
        return getPreferences().getString(key, "");
    }

    /**
     * Sets the given {@link Integer} value with the given key in the preferences.
     *
     * @param key   The key to store in the value under.
     * @param value The value to be stored.
     */
    public void setInteger(String key, int value) {
        getEditor().putInt(key, value).commit();
    }

    /**
     * Gets the {@link Integer} value stored for the given key preference.
     *
     * @param key the key to search for in preferences
     * @return {@link Integer} stored at the key.
     */
    public int getInteger(String key) {
        return getPreferences().getInt(key, 0);
    }

    /**
     * Sets the given {@link Boolean} value with the given key in the preferences.
     *
     * @param key   The key to store in the value under.
     * @param value The value to be stored.
     */
    public void setBoolean(String key, boolean value) {
        getEditor().putBoolean(key, value).commit();
    }

    /**
     * Gets the {@link Boolean} value stored for the given key preference.
     *
     * @param key the key to search for in preferences.
     * @return {@link Boolean} stored at the key.
     */
    public boolean getBoolean(String key) {
        return getPreferences().getBoolean(key, false);
    }

    /**
     * Stores the users auth token in shared preferences.
     *
     * @param token The token to be stored.
     */
    public void setToken(String token) {
        setString("AUTH_TOKEN", token);
    }

    /**
     * Gets the users auth token in shared preferences.
     */
    public String getToken() {
        return getString("AUTH_TOKEN");
    }

    /**
     * This will remove the given string key from the local storage if it exists.
     *
     * @param s The key to find and remove.
     */
    public void remove(String s) {
        getEditor().remove(s).commit();
    }

    /**
     * Removes the token from the local storage.
     */
    public void removeToken() {
        remove("AUTH_TOKEN");
    }

    /**
     * Gets the preference editor of the application.
     *
     * @return {@link SharedPreferences.Editor} object the current activity.
     */
    private SharedPreferences.Editor getEditor() {
        return getPreferences().edit();
    }

    /**
     * Gets the shared preferences of the application.
     *
     * @return {@link SharedPreferences} object the current activity.
     */
    private SharedPreferences getPreferences() {
        return currentActivity.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
    }
}
