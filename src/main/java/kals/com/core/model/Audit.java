package kals.com.core.model;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class Audit {

    private ZonedDateTime createdDate;
    private ZonedDateTime modifiedDate;
    private String createdBy;
    private String modifiedBy;

}
