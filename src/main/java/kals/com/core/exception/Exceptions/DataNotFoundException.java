package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

public class DataNotFoundException extends BaseException {
    DataNotFoundException(String errorCode) {
        super(errorCode);
    }
}
