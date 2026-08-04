package io.kals.core.exception.Exceptions;

import io.kals.core.exception.BaseException;

/**
 * Exception thrown when a mandatory HTTP request header is missing.
 */
public class HeaderNotFoundException extends BaseException {
    HeaderNotFoundException(String errorCode) {
        super(errorCode);
    }
}
