package kals.com.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Model representing the health status and metadata of the application.
 * Typically used by health-check endpoints.
 */
@Getter
@Setter
@Builder
public class ApplicationHealthStatus {

    private String service;
    private String status;
    private String version;

}
