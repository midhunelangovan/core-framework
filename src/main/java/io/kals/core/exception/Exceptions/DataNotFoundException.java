package io.kals.core.exception.Exceptions;

import io.kals.core.exception.BaseException;

/**
 * Exception thrown when requested data cannot be found.
 */
public class DataNotFoundException extends BaseException {
    DataNotFoundException(String errorCode) {
        super(errorCode);
    }
}
