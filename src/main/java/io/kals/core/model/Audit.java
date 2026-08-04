package io.kals.core.model;

import io.kals.core.entity.AuditEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * DTO representing standard auditing metadata.
 * Mirrors the fields found in {@link AuditEntity}.
 */
@Getter
@Setter
public class Audit {

    private ZonedDateTime createdDate;
    private ZonedDateTime modifiedDate;
    private String createdBy;
    private String modifiedBy;

}
