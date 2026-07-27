package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

public class ColumnNotFoundException extends BaseException {
    ColumnNotFoundException(String errorCode) {
        super(errorCode);
    }
}
