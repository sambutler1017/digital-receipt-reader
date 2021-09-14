package com.ridge.digitalreceiptreader.app.user.domain.request;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Get Request used for parameters to controller method.
 *
 * @author Sam Butler
 * @since July 30, 2021
 */
public class UserGetRequest {
    private Set<Integer> id;

    private Set<String> email;

    private Set<String> firstName;

    private Set<String> lastName;

    public Set<Integer> getId() {
        return id;
    }

    public void setId(Set<Integer> id) {
        this.id = id;
    }

    public Set<String> getEmail() {
        return email;
    }

    public void setEmail(Set<String> email) {
        this.email = email;
    }

    public Set<String> getFirstName() {
        return firstName;
    }

    public void setFirstName(Set<String> firstName) {
        this.firstName = firstName;
    }

    public Set<String> getLastName() {
        return lastName;
    }

    public void setLastName(Set<String> lastName) {
        this.lastName = lastName;
    }

    /**
     * Generate a param map for a url with the given object data
     *
     * @return {@link String} of the params
     */
    public String getUrlParamPath() {
        String idString = "";
        String emailString = "";
        String firstNameString = "";
        String lastNameString = "";

        if (id != null)
            idString += "id=" + StringUtils.collectionToDelimitedString(id, ",");
        if (email != null)
            emailString += "email=" + StringUtils.collectionToDelimitedString(email, ",");
        if (firstName != null)
            firstNameString += "firstName=" + StringUtils.collectionToDelimitedString(firstName, ",");
        if (lastName != null)
            lastNameString += "lastName=" + StringUtils.collectionToDelimitedString(lastName, ",");

        String endpointParams = Arrays.asList(idString, emailString, firstNameString, lastNameString).stream()
                .filter(v -> v != "").collect(Collectors.joining("&"));

        return endpointParams;
    }
}
