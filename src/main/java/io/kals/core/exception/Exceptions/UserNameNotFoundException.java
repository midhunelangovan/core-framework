package io.kals.core.exception.Exceptions;

import io.kals.core.exception.BaseException;

/**
 * Exception thrown when a user name or credentials cannot be located.
 */
public class UserNameNotFoundException extends BaseException {
    UserNameNotFoundException(String errorCode) {
        super(errorCode);
    }
}
