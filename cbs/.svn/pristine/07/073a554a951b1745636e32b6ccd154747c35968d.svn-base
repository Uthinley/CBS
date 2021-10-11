package com.springapp.mvc.data.db.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by Dechen Wangdi on 4/7/2020.
 */

@Entity
@Table(name = "tblcardtransaction")
public class CardTransaction implements Serializable {

    //region private variables.
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CID")
    private String cid;

    @Column(name = "CARDTYPE")
    private String cardType;

    @Column(name = "TXNDATE")
    private String txnDate;

    @Column(name = "MARCHATNAME")
    private String merchantName;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "MCC")
    private String mcc;

    @Column(name = "TXNAMOUNT")
    private BigDecimal txnAmount;

    @Column(name = "BANK")
    private String bank;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "CREATEDDATE")
    private Date createdDate;

    @Column(name = "CREATEDBY")
    private String createdBy;
    //endregion

    //region getter and setter.

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    //endregion
}


