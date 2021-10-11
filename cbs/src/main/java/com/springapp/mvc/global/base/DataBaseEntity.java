package com.springapp.mvc.global.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Dechen Wangdi on 3/25/2020.
 */@MappedSuperclass
public class DataBaseEntity {

    @NotNull
    @Column(name = "createdby")
    private String createdBy;
    @NotNull
    @Column(name = "createddate")
    private Date createdDate;

    @Column(name = "modifiedby")
    private Date modifiedBy;
    @Column(name = "modifieddate")
    private Date modifiedDate;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Date modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
