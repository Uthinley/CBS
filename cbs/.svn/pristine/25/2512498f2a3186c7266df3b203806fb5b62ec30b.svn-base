package com.springapp.mvc.global.enumeration;

/**
 * Created by Dechen Wangdi on 12/18/2019.
 */

public enum StatusCode {
    ACTIVE(1, "Active"),
    IN_ACTIVE(0, "Inactive");

    private Integer value;
    private String text;

    StatusCode(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public String find(Integer value) {
        if (value.equals(StatusCode.ACTIVE.getValue())) {
            return StatusCode.ACTIVE.getText();
        }
        if (value.equals(StatusCode.IN_ACTIVE.getValue())) {
            return StatusCode.IN_ACTIVE.getText();
        }
        return null;
    }
}
