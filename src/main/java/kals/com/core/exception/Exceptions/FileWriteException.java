package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

public class FileWriteException extends BaseException {
    FileWriteException(String errorCode) {
        super(errorCode);
    }
}
