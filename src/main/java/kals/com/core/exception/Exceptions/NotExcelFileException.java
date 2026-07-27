package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

public class NotExcelFileException extends BaseException {
    NotExcelFileException(String errorCode) {
        super(errorCode);
    }
}
