package kals.com.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApplicationHealthStatus {

    private String service;
    private String status;
    private String version;

}
