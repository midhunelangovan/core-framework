package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

/**
 * Exception thrown when entity or DTO validation fails before an operation.
 */
public class ValidationFailedException extends BaseException {
    public ValidationFailedException(String errorCode) {
        super(errorCode);
    }
}