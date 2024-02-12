package com.behrouztakhti.security.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

/**
 * This is MappedSuperclass in which we have some common columns that are suitable for preventing of code repetitive.
 * This used hibernate MappedSuperclass inheritance.
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 */
@MappedSuperclass
public class BaseEntity {

    @Column(name = "DESCRIPTION")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
