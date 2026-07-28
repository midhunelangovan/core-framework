package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

/**
 * Exception thrown when a requested resource (like an entity by ID) does not exist.
 */
public class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException(String errorCode) {
        super(errorCode);
    }

    public ResourceNotFoundException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

}
