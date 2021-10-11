package com.springapp.mvc.global.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * ====================================================================
 * Created by nimayoezer on 22/08/2018.
 * Description:
 * ====================================================================
 * Modified by: Dechen wangdi.
 * Modified date:
 * Purpose:
 * ====================================================================
 */
@MappedSuperclass
public class BaseEntity {

    @NotNull
    @Column(name = "CREATEDBY")
    private String createdBy;
    @NotNull
    @Column(name = "CREATEDDATE")
    private Date createdDate;

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
}
