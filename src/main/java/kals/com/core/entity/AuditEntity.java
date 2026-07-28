package kals.com.core.entity;

import jakarta.annotation.PreDestroy;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import kals.com.core.utility.UserUtil;
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
    private ZonedDateTime createdDate;
    @LastModifiedDate
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
        modifiedBy = "modified" + UserUtil.getUserName();
    }


}
