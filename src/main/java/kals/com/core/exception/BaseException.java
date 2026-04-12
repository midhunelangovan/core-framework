package kals.com.core.exception;

import kals.com.core.model.ErrorResponse;
import org.springframework.context.MessageSource;

import java.time.LocalDateTime;
import java.util.Locale;

public class BaseException extends RuntimeException {

    private final String errorCode;
    private String errorMessage;

    public BaseException(String errorCode) {
        this.errorCode = errorCode;
    }

    public BaseException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }


    public ErrorResponse handleExceptionResponse(MessageSource messageSource) {
        String defaultMessage = errorMessage == null ?
                "Requested resource not found" :
                errorMessage;
        String message = messageSource.getMessage(errorCode, null, defaultMessage, Locale.ENGLISH);
        return buildErrorResponse(message);
    }

    private ErrorResponse buildErrorResponse(String message) {
        return ErrorResponse.builder()
                .errorCode(errorCode)
                .message(message)
                .timeStamp(LocalDateTime.now())
                .build();
    }
}
