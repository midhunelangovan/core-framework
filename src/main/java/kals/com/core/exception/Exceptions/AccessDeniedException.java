package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

public class AccessDeniedException extends BaseException {
    public AccessDeniedException(String errorCode) {
        super(errorCode);
    }
}
