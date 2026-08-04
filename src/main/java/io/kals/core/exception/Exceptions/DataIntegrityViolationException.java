package io.kals.core.exception.Exceptions;

import io.kals.core.exception.BaseException;

/**
 * Exception thrown when an operation violates data integrity constraints (e.g. duplicate keys).
 */
public class DataIntegrityViolationException extends BaseException {

    public DataIntegrityViolationException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public DataIntegrityViolationException(String errorCode) {
        super(errorCode);
    }
}
