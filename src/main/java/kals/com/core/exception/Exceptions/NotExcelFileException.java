package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

/**
 * Exception thrown when a file is expected to be an Excel file, but it is not.
 */
public class NotExcelFileException extends BaseException {
    NotExcelFileException(String errorCode) {
        super(errorCode);
    }
}
