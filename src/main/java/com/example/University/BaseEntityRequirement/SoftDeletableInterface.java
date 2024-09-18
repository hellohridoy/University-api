package com.example.University.BaseEntityRequirement;

import java.util.Date;

/**
 * SoftDeletable Interface.
 */
public interface SoftDeletableInterface {

    void setDeletedBy(Long deletedBy);

    Long getDeletedBy();

    void setDeletedAt(Date deletedAt);

    Date getDeletedAt();

    void setDeleted(Boolean deleted);

    Boolean getDeleted();
}
