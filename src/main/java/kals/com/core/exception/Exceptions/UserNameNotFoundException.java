package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

/**
 * Exception thrown when a user name or credentials cannot be located.
 */
public class UserNameNotFoundException extends BaseException {
    UserNameNotFoundException(String errorCode) {
        super(errorCode);
    }
}
