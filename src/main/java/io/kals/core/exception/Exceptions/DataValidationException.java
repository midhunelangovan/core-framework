package io.kals.core.exception.Exceptions;

import io.kals.core.exception.BaseException;

/**
 * Exception thrown when data fails business logic validation.
 */
public class DataValidationException extends BaseException {
    public DataValidationException(String errorCode) {
        super(errorCode);
    }
}
