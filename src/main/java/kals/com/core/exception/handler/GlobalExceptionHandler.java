package kals.com.core.exception.handler;

import kals.com.core.exception.BaseException;
import kals.com.core.exception.Exceptions.*;
import kals.com.core.model.ErrorResponse;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.resource.NoResourceFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler extends BaseException {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @ExceptionHandler({
            BadRequestException.class,
            HeaderNotFoundException.class,
            FileFormatException.class,
            FileWriteException.class,
            ValidationFailedException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleDataValidationException(BaseException exception) {
        return handleExceptionResponse(messageSource);
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handlePredefinedException(MethodArgumentNotValidException ex){
        return ErrorResponse.builder()
                .errorCode("KALS001")
                .message("Constraints are not satisfied")
                .build();
    }

    @ExceptionHandler({
            AccessDeniedException.class
    })
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public @ResponseBody ErrorResponse handleAccessDeniedException(BaseException exception) {
        return exception.handleExceptionResponse(messageSource);
    }

    @ExceptionHandler({
            ResourceNotFoundException.class,
            MissingImplementationException.class,
            UserNameNotFoundException.class,
            ColumnNotFoundException.class,
            DataNotFoundException.class,
            NotExcelFileException.class,
            InvalidDateRangeException.class,
            NoResourceFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponse handleNotFoundException(BaseException exception) {
        return exception.handleExceptionResponse(messageSource);
    }

    @ExceptionHandler({
            UserException.class,
            DataIntegrityViolationException.class,
            DataValidationException.class,
            org.springframework.dao.DataIntegrityViolationException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody ErrorResponse handleConflictException(BaseException exception) {
        return exception.handleExceptionResponse(messageSource);
    }

    @ExceptionHandler({
            RuntimeException.class,
            Exception.class,
            MissingRequestHeaderException.class,
            IllegalStateException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handlePredefinedBadRequestException(BaseException exception) {
        return exception.handleExceptionResponse(messageSource);
    }

    @ExceptionHandler({
            ZonedDateTimeFormatException.class
    })
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public @ResponseBody ErrorResponse handleUnprocessableEntityException(BaseException exception) {
        return exception.handleExceptionResponse(messageSource);
    }

    @ExceptionHandler({
            BaseException.class
    })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody ErrorResponse handleUnauthorizedException(BaseException exception) {
        return exception.handleExceptionResponse(messageSource);
    }

    @ExceptionHandler({
            HttpClientErrorException.class,
            HttpClientErrorException.MethodNotAllowed.class,
            HttpRequestMethodNotSupportedException.class
    })
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public @ResponseBody ErrorResponse handleMethodNotFoundException(BaseException exception) {
        return exception.handleExceptionResponse(messageSource);
    }

}
