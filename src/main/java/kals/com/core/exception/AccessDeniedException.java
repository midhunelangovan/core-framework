package kals.com.core.exception;

public class AccessDeniedException extends BaseException {

    public AccessDeniedException(String errorCode) {
        super(errorCode);
    }
}
