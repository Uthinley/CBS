package com.springapp.mvc.global.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ====================================================================
 * Created by DELL on 28/05/2018.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified date:
 * Purpose:
 * ====================================================================
 */
public class CurrentUser {
    private Integer userID;
    private String userName;
    private String fullName;
    private String branchCode;
    private String branchName;
    private Integer groupId;
    private Date serverDate; //Calender Date
    private BigDecimal bankId;
    private BigDecimal dzongkhagId;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
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

    public Date getServerDate() {
        return serverDate;
    }

    public void setServerDate(Date serverDate) {
        this.serverDate = serverDate;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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
}
