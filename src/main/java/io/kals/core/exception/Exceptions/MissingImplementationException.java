package io.kals.core.exception.Exceptions;

import io.kals.core.exception.BaseException;

/**
 * Exception thrown when a required method or feature is not implemented by the service.
 */
public class MissingImplementationException extends BaseException {
    MissingImplementationException(String errorCode) {
        super(errorCode);
    }
}