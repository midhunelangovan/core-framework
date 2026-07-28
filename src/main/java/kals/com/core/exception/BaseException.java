package kals.com.core.exception;

import kals.com.core.model.ErrorResponse;
import org.springframework.context.MessageSource;

import java.time.LocalDateTime;
import java.util.Locale;

/**
 * Base abstract exception for all custom framework exceptions.
 * Encapsulates an error code and a default message. Can resolve messages from a Spring {@link MessageSource}.
 */
public class BaseException extends RuntimeException {

    private String errorCode;
    private String errorMessage;

    /**
     * Default constructor.
     */
    public BaseException() {
    }

    /**
     * Constructs a new exception with an error code.
     * @param errorCode the application specific error code
     */
    public BaseException(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Constructs a new exception with an error code and default message.
     * @param errorCode the application specific error code
     * @param errorMessage the default error message
     */
    public BaseException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * Translates this exception into an {@link ErrorResponse}.
     * Attempts to resolve the error code using the provided MessageSource.
     *
     * @param messageSource the Spring message source for i18n
     * @return the structured ErrorResponse
     */
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
