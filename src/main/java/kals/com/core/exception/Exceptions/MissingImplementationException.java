package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

public class MissingImplementationException extends BaseException {
    MissingImplementationException(String errorCode) {
        super(errorCode);
    }
}