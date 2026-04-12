package kals.com.core.entity;

import jakarta.annotation.PreDestroy;
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
    public void preAuditPersist(){
        createdDate = ZonedDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        modifiedDate = ZonedDateTime.now();
    }

    @PreDestroy
    public void preDestroy(){
        modifiedDate = ZonedDateTime.now();
    }


}
