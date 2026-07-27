package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

public class ValidationFailedException  extends BaseException {
    public ValidationFailedException(String errorCode) {
        super(errorCode);
    }
}