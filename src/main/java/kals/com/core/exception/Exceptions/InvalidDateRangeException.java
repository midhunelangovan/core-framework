package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

public class InvalidDateRangeException extends BaseException {
    InvalidDateRangeException(String errorCode) {
        super(errorCode);
    }
}
