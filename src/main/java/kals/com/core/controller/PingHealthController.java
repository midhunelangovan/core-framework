package kals.com.core.controller;


import kals.com.core.model.ApplicationHealthStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to provide health and status check endpoints.
 * Very useful for deployment environments to check if the application is alive.
 */
@RestController
@RequestMapping("/ping")
public class PingHealthController {

    @Value("${spring.application.name:}")
    private String applicationName;

    @Value("${spring.project.version:}")
    private String applicationVersion;

    /**
     * Endpoint to retrieve the current health status of the application.
     *
     * @return an {@link ApplicationHealthStatus} detailing the service name, version, and running status
     */
    @GetMapping
    public ApplicationHealthStatus getApplicationStatus() {
        return ApplicationHealthStatus.builder()
                .service(applicationName)
                .version(applicationVersion)
                .status("Application is Running Successfully!")
                .build();
    }

}

