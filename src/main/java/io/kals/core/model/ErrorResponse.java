package io.kals.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Standardized error response model used by the GlobalExceptionHandler.
 * Ensures API clients receive a consistent error format.
 */
@Getter
@Setter
@Builder
public class ErrorResponse {

    private String errorCode;
    private String message;
    private LocalDateTime timeStamp;

}