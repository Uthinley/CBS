package com.springapp.mvc.data.db.dto;

import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelRow;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Dechen Wangdi on 3/25/2020.
 */
public class BankDepositDTO implements Serializable {

    //region private variables.
    @ExcelRow
    private int rowIndex;

    @ExcelCell(0)
    private String name;

    @ExcelCell(1)
    private String cid;

    @ExcelCell(2)
    private String cardType;

    @ExcelCell(3)
    private String txnDate;

    @ExcelCell(4)
    private String merchantName;

    @ExcelCell(5)
    private String country;

    @ExcelCell(6)
    private String mcc;

    @ExcelCell(7)
    private BigDecimal txnAmount;

    @ExcelCell(8)
    private String bank;

    @ExcelCell(9)
    private String currency;
    //endregion


    //region getter and setter.

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(String txnDate) {
        this.txnDate = txnDate;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public BigDecimal getTxnAmount() {
        return txnAmount;
    }

    public void setTxnAmount(BigDecimal txnAmount) {
        this.txnAmount = txnAmount;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    //endregion
}
