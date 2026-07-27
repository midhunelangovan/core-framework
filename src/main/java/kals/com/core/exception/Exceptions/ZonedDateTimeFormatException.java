package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

public class ZonedDateTimeFormatException extends BaseException {
    ZonedDateTimeFormatException(String errorCode) {
        super(errorCode);
    }
}