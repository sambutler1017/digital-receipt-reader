package com.ridge.digitalreceiptreader.common.exceptions;

/**
 * Exception for when user sends invalid HttpMethod type.
 *
 * @author Sam Butler
 * @since July 31, 2021
 */
public class ApiMethodException extends Exception {
    public ApiMethodException(String message) {
        super(message);
    }
}
