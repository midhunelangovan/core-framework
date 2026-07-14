package kals.com.core.controller;


import kals.com.core.model.ApplicationHealthStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{*context-path}/ping")
public class PingHealthController {

    @Value("${spring.application.name:}")
    private String applicationName;

    @Value("${spring.project.version:}")
    private String applicationVersion;


    @GetMapping
    public ApplicationHealthStatus getApplicationStatus(){
        return ApplicationHealthStatus.builder()
                .service(applicationName)
                .version(applicationVersion)
                .status("Application is Running Successfully!")
                .build();
    }

}
