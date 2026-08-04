package io.kals.core.exception.Exceptions;

import io.kals.core.exception.BaseException;

/**
 * Exception thrown when an uploaded file does not match the required format.
 */
public class FileFormatException extends BaseException {
    FileFormatException(String errorCode) {
        super(errorCode);
    }
}
