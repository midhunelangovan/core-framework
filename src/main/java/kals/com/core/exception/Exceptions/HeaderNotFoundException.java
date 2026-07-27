package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

public class HeaderNotFoundException extends BaseException {
    HeaderNotFoundException(String errorCode) {
        super(errorCode);
    }
}
