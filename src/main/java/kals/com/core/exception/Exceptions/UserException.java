package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

public class UserException extends BaseException {
    UserException(String errorCode) {
        super(errorCode);
    }
}