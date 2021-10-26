package com.ridge.digitalreceiptreader.service;

import android.app.Activity;

import com.ridge.digitalreceiptreader.common.enums.WebRole;
import com.ridge.digitalreceiptreader.service.util.LocalStorageService;

import java.util.Base64;

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
     * Get the current userId from the request headers token
     *
     * @return int of the userId from the current token
     */
    public int getRequiredUserId() {
        try {
            return Integer.parseInt(getParamFromToken("userId"));
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Get the current email from the request headers token
     *
     * @return String of the email from the current token
     */
    public String getRequiredEmail() {
        try {
            return getParamFromToken("email");
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Get the current webRole from the request headers token
     *
     * @return String of the webRole from the current token
     */
    public WebRole getWebRole() {
        try {
            return WebRole.valueOf(getParamFromToken("webRole"));
        } catch (Exception e) {
            return WebRole.USER;
        }
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

        String startOfParam = payload.substring(payload.indexOf(param));
        return startOfParam.substring(startOfParam.indexOf("\":"), startOfParam.indexOf(",")).replace("\":", "");
    }
}
