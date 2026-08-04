package io.kals.core.exception.Exceptions;

import io.kals.core.exception.BaseException;

/**
 * Exception thrown when a user attempts to access a resource without sufficient permissions.
 */
public class AccessDeniedException extends BaseException {
    public AccessDeniedException(String errorCode) {
        super(errorCode);
    }
}
