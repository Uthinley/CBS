package com.springapp.mvc.global.enumeration;

public enum TransactionEnum {
    MOBILE_BANKING(1, "Mobile Banking"),
    QR_PAYMENT(2, "QR Payment"),
    ATM(3, "ATM"),
    RUPAY_ATM(4, "RuPay ATM"),
    MOBILE_WALLET(5, "Mobile Wallet"),
    LARGE_VALUE_CHEQUE(6, "Large Value-Cheque"),
    LARGE_VALUE_GIFT(7, "Large Value-GIFT(RTGS)"),
    GIFT_BITS_AND_BULK(8, "GIFT- BITS and Bulk");


    private Integer value;
    private String text;

    TransactionEnum(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    public static String findByValue(Integer value) {
        for (TransactionEnum trans : values()) {
            if (trans.value().equals(value)) {
                return trans.text;
            }
        }
        return null;
    }

   /* public static Verbosity findByAbbr(String abbr){
        for(Verbosity v : values()){
            if( v.abbr().equals(abbr)){
                return v;
            }
        }
        return null;
    }*/

    public Integer value() {
        return value;
    }

    public String text() {
        return text;
    }
}
