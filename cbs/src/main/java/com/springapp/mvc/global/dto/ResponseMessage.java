package com.springapp.mvc.global.dto;

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
public class ResponseMessage {
    private Integer status;
    private String text;
    private Object dto;
    private String value;

    // Data for Approval Request
    private String cmdFlag;
    private String screenId;
    private String userId;
    private Date systemOpenDate;
    private String requestId;
    private String responseText;

    //region Setter Declaration

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getDto() {
        return dto;
    }

    public void setDto(Object dto) {
        this.dto = dto;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCmdFlag() {
        return cmdFlag;
    }

    public void setCmdFlag(String cmdFlag) {
        this.cmdFlag = cmdFlag;
    }

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getSystemOpenDate() {
        return systemOpenDate;
    }

    public void setSystemOpenDate(Date systemOpenDate) {
        this.systemOpenDate = systemOpenDate;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }


}
