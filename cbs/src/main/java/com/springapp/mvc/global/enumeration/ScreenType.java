package com.springapp.mvc.global.enumeration;

/**
 * Created by Dechen Wangdi on 3/3/2020.
 */
public enum ScreenType {

    SETUP('S', "Setup"),
    DASHBOARD('D', "Dashboard"),
    OPERATION('O', "Operation"),
    REPORT('R', "Report");

    private Character value;
    private String text;

    ScreenType(Character value, String text) {
        this.value = value;
        this.text = text;
    }

    public Character getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

}
