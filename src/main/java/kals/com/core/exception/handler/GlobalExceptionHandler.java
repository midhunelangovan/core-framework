package kals.com.core.exception.handler;


import kals.com.core.exception.DataValidationException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import kals.com.core.exception.BaseException;
import kals.com.core.exception.ResourceNotFoundException;
import kals.com.core.model.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(
            value = {ResourceNotFoundException.class}
    )
    public @ResponseStatus(value = HttpStatus.NOT_FOUND) ErrorResponse handleNotFoundGlobalException(BaseException e) {
        return e.handleExceptionResponse(messageSource);
    }


    @ExceptionHandler(
            value = {DataValidationException.class}
    )
    public @ResponseStatus(value = HttpStatus.CONFLICT) ErrorResponse handleConflictGlobalException(BaseException e) {
        return e.handleExceptionResponse(messageSource);
    }


}