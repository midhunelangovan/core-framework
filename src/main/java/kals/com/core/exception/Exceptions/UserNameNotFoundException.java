package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

public class UserNameNotFoundException extends BaseException {
    UserNameNotFoundException(String errorCode) {
        super(errorCode);
    }
}
