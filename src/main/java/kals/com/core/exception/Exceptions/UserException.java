package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

/**
 * Exception thrown for generic user-related errors or state issues.
 */
public class UserException extends BaseException {
    UserException(String errorCode) {
        super(errorCode);
    }
}