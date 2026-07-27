package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

public class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException(String errorCode) {
        super(errorCode);
    }

    public ResourceNotFoundException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

}
