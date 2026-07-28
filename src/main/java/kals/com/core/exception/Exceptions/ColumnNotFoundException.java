package kals.com.core.exception.Exceptions;

import kals.com.core.exception.BaseException;

/**
 * Exception thrown when a requested column or field is not found in the entity/database.
 */
public class ColumnNotFoundException extends BaseException {
    ColumnNotFoundException(String errorCode) {
        super(errorCode);
    }
}
