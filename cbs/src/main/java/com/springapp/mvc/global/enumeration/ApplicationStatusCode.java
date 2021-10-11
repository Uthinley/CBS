package com.springapp.mvc.global.enumeration;

public enum ApplicationStatusCode {
    SUBMITTED(1, "SUBMITTED"),
    REVIEWED(2, "REVIEWED"),
    RETURNED(3, "RETURNED"),
    APPROVED(4, "APPROVED");

    private Integer value;
    private String text;

    ApplicationStatusCode(int value, String text) {
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
        if (value.equals(ApplicationStatusCode.SUBMITTED.getValue())) {
            return ApplicationStatusCode.SUBMITTED.getText();
        }
        if (value.equals(ApplicationStatusCode.REVIEWED.getValue())) {
            return ApplicationStatusCode.REVIEWED.getText();
        }
        if (value.equals(ApplicationStatusCode.RETURNED.getValue())) {
            return ApplicationStatusCode.RETURNED.getText();
        }
        if (value.equals(ApplicationStatusCode.APPROVED.getValue())) {
            return ApplicationStatusCode.APPROVED.getText();
        }
        return null;
    }

}
