package io.kals.core.exception.handler;

import io.kals.core.exception.BaseException;
import io.kals.core.exception.Exceptions.*;
import io.kals.core.model.ErrorResponse;
import org.hibernate.exception.ConstraintViolationException;
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

import java.time.LocalDateTime;
import java.util.Locale;


/**
 * Centralized exception handler for the entire framework.
 * Catches application-specific exceptions and predefined framework exceptions,
 * converting them into standardized {@link ErrorResponse} objects with appropriate HTTP status codes.
 */
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
    public @ResponseBody ErrorResponse handlePredefinedException(MethodArgumentNotValidException ex) {
        return ErrorResponse.builder()
                .errorCode("KALS001")
                .message("Constraints are not satisfied")
                .build();
    }

    @ExceptionHandler(
            org.springframework.dao.DataIntegrityViolationException.class
    )
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody ErrorResponse handleDataIntegrityViolationException(org.springframework.dao.DataIntegrityViolationException ex) {
        String errorCode = ex.getCause() instanceof ConstraintViolationException ? ((ConstraintViolationException) ex.getCause()).getConstraintName() : ex.getMessage();
        String errorMessage = errorCode != null? messageSource.getMessage(errorCode,null, Locale.ENGLISH) : ex.getMessage();
        return ErrorResponse.builder()
                .errorCode(errorCode)
                .message(errorMessage)
                .build();
    }


    @ExceptionHandler({
            AccessDeniedException.class
    })
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public @ResponseBody ErrorResponse handleAccessDeniedException(BaseException exception) {
        return exception.handleExceptionResponse(messageSource);
    }

    @ExceptionHandler(
            NoResourceFoundException.class
    )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponse handleNoResourceFoundException(NoResourceFoundException ex) {
        return ErrorResponse.builder()
                .errorCode("RESOURCE_NOT_FOUND")
                .message(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler({
            ResourceNotFoundException.class,
            MissingImplementationException.class,
            UserNameNotFoundException.class,
            ColumnNotFoundException.class,
            DataNotFoundException.class,
            NotExcelFileException.class,
            InvalidDateRangeException.class,
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponse handleNotFoundException(BaseException exception) {
        return exception.handleExceptionResponse(messageSource);
    }

    @ExceptionHandler({
            UserException.class,
            DataIntegrityViolationException.class,
            DataValidationException.class,
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
    public @ResponseBody ErrorResponse handleMethodNotFoundException(Exception exception) {
        return ErrorResponse.builder()
                .errorCode("BAD_REQUEST")
                .message(exception.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
    }

}
