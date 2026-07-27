package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

public class DataIntegrityViolationException extends BaseException {

    public DataIntegrityViolationException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public DataIntegrityViolationException(String errorCode) {
        super(errorCode);
    }
}
