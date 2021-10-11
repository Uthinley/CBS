package com.springapp.mvc.auth;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ====================================================================
 * Created by DELL on 25/07/2018.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified date:
 * Purpose:
 * ====================================================================
 */
public class LoginDTO {
    private BigDecimal userId;
    private String userName;
    private String password;
    private String userFullName;
    private String userStatus;
    private Integer groupId;
    private Integer groupStatus;
    private String employeeCode;
    private String branchCode;
    private String branchName;
    private BigDecimal bankId;
    private BigDecimal dzongkhagId;
    private BigDecimal agencyStatus;
    private Date validityDate;
    private boolean passwordChangeYN;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
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

    public Integer getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(Integer groupStatus) {
        this.groupStatus = groupStatus;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public BigDecimal getBankId() {
        return bankId;
    }

    public void setBankId(BigDecimal bankId) {
        this.bankId = bankId;
    }

    public BigDecimal getDzongkhagId() {
        return dzongkhagId;
    }

    public void setDzongkhagId(BigDecimal dzongkhagId) {
        this.dzongkhagId = dzongkhagId;
    }

    public Date getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

    public boolean isPasswordChangeYN() {
        return passwordChangeYN;
    }

    public void setPasswordChangeYN(boolean passwordChangeYN) {
        this.passwordChangeYN = passwordChangeYN;
    }

    public BigDecimal getAgencyStatus() {
        return agencyStatus;
    }

    public void setAgencyStatus(BigDecimal agencyStatus) {
        this.agencyStatus = agencyStatus;
    }
}
