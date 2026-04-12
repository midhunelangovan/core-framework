package kals.com.core.exception;


public class ResourceNotFoundException extends BaseException {

    public ResourceNotFoundException(String errorCode) {
        super(errorCode);
    }

}