package io.kals.core.exception.Exceptions;

import io.kals.core.exception.BaseException;

/**
 * Exception thrown when an invalid date or time format is provided for a ZonedDateTime.
 */
public class ZonedDateTimeFormatException extends BaseException {
    ZonedDateTimeFormatException(String errorCode) {
        super(errorCode);
    }
}