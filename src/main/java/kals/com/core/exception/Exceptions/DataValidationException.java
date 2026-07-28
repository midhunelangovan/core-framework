package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

/**
 * Exception thrown when data fails business logic validation.
 */
public class DataValidationException extends BaseException {
    public DataValidationException(String errorCode) {
        super(errorCode);
    }
}
