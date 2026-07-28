package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

/**
 * Exception thrown when a provided date range is invalid (e.g., start date > end date).
 */
public class InvalidDateRangeException extends BaseException {
    InvalidDateRangeException(String errorCode) {
        super(errorCode);
    }
}
