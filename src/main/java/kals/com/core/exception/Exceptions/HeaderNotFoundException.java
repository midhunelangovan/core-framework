package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

/**
 * Exception thrown when a mandatory HTTP request header is missing.
 */
public class HeaderNotFoundException extends BaseException {
    HeaderNotFoundException(String errorCode) {
        super(errorCode);
    }
}
