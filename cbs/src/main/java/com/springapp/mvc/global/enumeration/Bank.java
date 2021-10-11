package com.springapp.mvc.global.enumeration;

/**
 * ====================================================================
 * Created by Nima Yoezer on 5/14/2020.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified on:
 * Changes made :
 * ====================================================================
 */
public enum Bank {
    BOBL(1, "BOBL"),
    BNBL(2, "BNBL"),
    BDBL(3, "BDBL"),
    TBL(4, "TBL"),
    DNPBL(5, "DNPBL");

    private Integer value;
    private String text;

    Bank(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

}
