package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

public class FileFormatException  extends BaseException {
    FileFormatException(String errorCode){
        super(errorCode);
    }
}
