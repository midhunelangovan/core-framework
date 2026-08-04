package io.kals.core.exception.Exceptions;

import io.kals.core.exception.BaseException;

/**
 * Exception thrown when a file is expected to be an Excel file, but it is not.
 */
public class NotExcelFileException extends BaseException {
    NotExcelFileException(String errorCode) {
        super(errorCode);
    }
}
