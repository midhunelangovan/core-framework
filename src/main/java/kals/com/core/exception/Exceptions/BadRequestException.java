package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

public class BadRequestException extends BaseException {
    BadRequestException(String errorCode) {
        super(errorCode);
    }
}
