package com.example.University.BaseEntityRequirement;

import java.util.Date;

/**
 * Interface for Auditable.
 */
public interface AuditableInterface {

    void setCreatedBy(Long createdBy);

    Long getCreatedBy();

    void setCreatedAt(Date createdAt);

    Date getCreatedAt();

    void setUpdatedBy(Long updatedBy);

    Date getUpdatedAt();

    void setVersion(Long version);

    Long getVersion();
}
