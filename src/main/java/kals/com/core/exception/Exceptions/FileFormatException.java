package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

/**
 * Exception thrown when an uploaded file does not match the required format.
 */
public class FileFormatException extends BaseException {
    FileFormatException(String errorCode) {
        super(errorCode);
    }
}
