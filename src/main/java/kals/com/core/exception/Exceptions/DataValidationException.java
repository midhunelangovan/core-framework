package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

public class DataValidationException extends BaseException {
    public DataValidationException(String errorCode) {
        super(errorCode);
    }
}
