package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

/**
 * Exception thrown when the incoming request is malformed or invalid.
 */
public class BadRequestException extends BaseException {
    BadRequestException(String errorCode) {
        super(errorCode);
    }
}
