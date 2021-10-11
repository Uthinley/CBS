package com.springapp.mvc.global.common;

import com.sun.istack.internal.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by Nima Yoezer on 3/26/2020.
 * Description :
 * Purpose:
 * Modified by :
 * Modified on :
 */
@Entity
@Table(name = "common_file_detail")
public class FileDetailEntity implements Serializable {
    @Id
    @NotNull
    @Column(name = "file_id")
    private BigInteger file_id;
    @Column(name = "file_name")
    private String file_name;
    @Column(name = "file_location")
    private String file_location;
    @Column(name = "file_type")
    private String file_type;
    @Column(name = "report_date")
    private Date report_date;

    @NotNull
    @Column(name = "createdby")
    private String createdBy;
    @NotNull
    @Column(name = "createddate")
    private Date createdDate;

    public BigInteger getFile_id() {
        return file_id;
    }

    public void setFile_id(BigInteger file_id) {
        this.file_id = file_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_location() {
        return file_location;
    }

    public void setFile_location(String file_location) {
        this.file_location = file_location;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public Date getReport_date() {
        return report_date;
    }

    public void setReport_date(Date report_date) {
        this.report_date = report_date;
    }

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
