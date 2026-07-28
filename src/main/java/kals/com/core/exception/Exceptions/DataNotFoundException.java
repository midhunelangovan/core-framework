package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

/**
 * Exception thrown when requested data cannot be found.
 */
public class DataNotFoundException extends BaseException {
    DataNotFoundException(String errorCode) {
        super(errorCode);
    }
}
