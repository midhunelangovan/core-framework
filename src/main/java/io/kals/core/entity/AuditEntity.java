package io.kals.core.entity;

import io.kals.security.utils.UserUtil;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Base JPA mapped superclass providing auditing capabilities.
 * Tracks creation and modification timestamps and user details.
 * Entities extending this class will automatically inherit these audit fields.
 */
@Getter
@Setter
@MappedSuperclass
public class AuditEntity implements Serializable {

    @CreatedDate
    @Column(name = "created_at")
    private ZonedDateTime createdDate;
    @LastModifiedDate
    @Column(name = "modified_at")
    private ZonedDateTime modifiedDate;
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String modifiedBy;


    @PrePersist
    public void preAuditPersist() {
        createdDate = ZonedDateTime.now();
        modifiedDate = ZonedDateTime.now();
        createdBy = UserUtil.getUserName();
        modifiedBy = UserUtil.getUserName();
    }

    @PreUpdate
    public void preUpdate() {
        modifiedDate = ZonedDateTime.now();
        modifiedBy = UserUtil.getUserName();
    }

    @PreDestroy
    public void preDestroy() {
        modifiedDate = ZonedDateTime.now();
        modifiedBy = UserUtil.getUserName();
    }


}
