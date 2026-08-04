package io.kals.core.exception.Exceptions;

import io.kals.core.exception.BaseException;

/**
 * Exception thrown when the application fails to write to a file or disk.
 */
public class FileWriteException extends BaseException {
    FileWriteException(String errorCode) {
        super(errorCode);
    }
}
