package kals.com.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErrorResponse {

    private String errorCode;
    private String message;
    private LocalDateTime timeStamp;

}