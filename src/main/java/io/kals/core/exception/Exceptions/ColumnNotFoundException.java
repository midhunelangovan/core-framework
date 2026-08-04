package io.kals.core.exception.Exceptions;

import io.kals.core.exception.BaseException;

/**
 * Exception thrown when a requested column or field is not found in the entity/database.
 */
public class ColumnNotFoundException extends BaseException {
    ColumnNotFoundException(String errorCode) {
        super(errorCode);
    }
}
