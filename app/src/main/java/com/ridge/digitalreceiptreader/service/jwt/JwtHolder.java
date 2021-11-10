package com.ridge.digitalreceiptreader.service.jwt;

import android.app.Activity;

import com.ridge.digitalreceiptreader.common.enums.WebRole;
import com.ridge.digitalreceiptreader.service.util.LocalStorageService;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JwtHolder class to get common information from token.
 *
 * @author Sam Butler
 * @since September 14, 2021
 */
public class JwtHolder {
    private final LocalStorageService localStorage;

    public JwtHolder(Activity act) {
        localStorage = new LocalStorageService(act);
    }

    /**
     * Get the current userId from the request headers token.
     *
     * @return {@link Integer} of the userId from the current token.
     */
    public int getRequiredUserId() {
        try {
            return Integer.parseInt(getParamFromToken("userId"));
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Get the current email from the request headers token.
     *
     * @return String of the email from the current token.
     */
    public String getRequiredEmail() {
        try {
            return getParamFromToken("email");
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Get the current webRole from the request headers token.
     *
     * @return String of the webRole from the current token.
     */
    public WebRole getWebRole() {
        try {
            return WebRole.valueOf(getParamFromToken("webRole"));
        } catch (Exception e) {
            return WebRole.USER;
        }
    }

    /**
     * Get the given string from the jwt token. If the string does not exist it will
     * return nothing.
     *
     * @param p The value to parse from the token.
     * @return {@link String} of the passed in string.
     */
    public String get(String p) {
        try {
            return getParamFromToken(p);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Checks to see if the user that opened the app already has a token.
     *
     * @return {@link Boolean} of the token status.
     */
    public boolean hasToken() {
        return localStorage.getToken().trim() != "";
    }

    /**
     * Parses the token and finds the param tag in the given token to get.
     *
     * @param param The param value to look for.
     * @return {@link String} of the value found.
     */
    private String getParamFromToken(String param) {
        String[] chunks = localStorage.getToken().split("\\.");
        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(chunks[1]));

        Matcher m = Pattern.compile(String.format("(?<=\\\"%s\\\":).*?(?=[,}])", param)).matcher(payload);

        return m.find() ? m.group(0).replace("\"", "") : "";
    }
}
