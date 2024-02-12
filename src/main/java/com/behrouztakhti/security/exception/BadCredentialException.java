package com.behrouztakhti.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * BadCredentialException.
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 * @see GlobalExceptionHandler
 */
public class BadCredentialException extends AuthenticationException {

    public BadCredentialException(String message) {
        super(message);
    }

    public BadCredentialException(String message, Throwable cause) {
        super(message, cause);
    }
}
