package com.springapp.mvc.setup.user;

import com.springapp.mvc.global.base.BaseEntity;
import com.sun.istack.internal.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * ====================================================================
 * Created by nimayoezer on 25/07/2018.
 * Description:
 * ====================================================================
 * Modified by:Dechen Wangdi
 * Modified date:
 * Purpose:
 * ====================================================================
 */
@Entity
@Table(name = "sa_user")
public class UserSetup extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -723583058586873479L;
    //region private column fields
    @NotNull
    @Column(name = "USRUSERID")
    private BigDecimal userId;

    @Id
    @NotNull
    @Column(unique = true, name = "USRUSERNAME")
    private String userName;

    @NotNull
    @Column(name = "USRFULLNAME")
    private String fullName;

    @NotNull
    @Column(name = "USRPASSWORD")
    private String password;

    @Column(name = "USREMPLOYEECODE")
    private String employeeCode;

    @NotNull
    @Column(name = "USRAGENCYCODE")
    private String agencyCode;

    @NotNull
    @Column(name = "USRUSERSTATUS")
    private String userStatus;

    @NotNull
    @Column(name = "USRGROUPID")
    private Integer groupId;

    @NotNull
    @Column(name = "USRBADCREDENTIAL")
    private Boolean isBadCredential;

    @NotNull
    @Column(name = "USRPASSEXPIRY")
    private Date passwordExpiryDate;
    //endregion

    //region getters and setters

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Boolean getIsBadCredential() {
        return isBadCredential;
    }

    public void setIsBadCredential(Boolean isBadCredential) {
        this.isBadCredential = isBadCredential;
    }

    public Date getPasswordExpiryDate() {
        return passwordExpiryDate;
    }

    public void setPasswordExpiryDate(Date passwordExpiryDate) {
        this.passwordExpiryDate = passwordExpiryDate;
    }
    //endregion
}
