package io.kals.core.exception.Exceptions;

import io.kals.core.exception.BaseException;

/**
 * Exception thrown when a provided date range is invalid (e.g., start date > end date).
 */
public class InvalidDateRangeException extends BaseException {
    InvalidDateRangeException(String errorCode) {
        super(errorCode);
    }
}
