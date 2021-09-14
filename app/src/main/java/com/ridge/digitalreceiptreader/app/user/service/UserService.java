package com.ridge.digitalreceiptreader.app.user.service;

import android.app.Activity;

import com.ridge.digitalreceiptreader.app.user.domain.User;
import com.ridge.digitalreceiptreader.common.api.ApiClient;

import org.springframework.http.ResponseEntity;

/**
 * User Service which will be the middle tier between
 * the application and the api.
 *
 * @author Sam Butler,
 * @since July 31, 2021
 */
public class UserService {
    private final String BASE_PATH = "api/user-app/users";
    private ApiClient apiClient;

    public UserService(Activity act) {
        apiClient = new ApiClient(act);
    }

    /**
     * Method that will get the current user that is logged in.
     *
     * @return {@link User} of the current user object.
     */
    public ResponseEntity<User> getCurrentUser() {
        return apiClient.get(String.format("%s/current-user", BASE_PATH), User.class);
    }

    // (Class<List<User>>)(Object)List.class
}
