package com.springapp.mvc.card;

import java.math.BigDecimal;
import java.util.Date;

public class CardDTO {
    private Integer id;
    private String cid;
    private String name;
    private String cardType;
    private String bank;
    private BigDecimal sanctionAmt;
    private BigDecimal usd_amt;
    private BigDecimal inr_amt;
    private BigDecimal usd_num;
    private BigDecimal inr_num;
    private String currency;
    private String createdBy;
    private Date createdDate;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public BigDecimal getSanctionAmt() {
        return sanctionAmt;
    }

    public void setSanctionAmt(BigDecimal sanctionAmt) {
        this.sanctionAmt = sanctionAmt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getUsd_amt() {
        return usd_amt;
    }

    public void setUsd_amt(BigDecimal usd_amt) {
        this.usd_amt = usd_amt;
    }

    public BigDecimal getInr_amt() {
        return inr_amt;
    }

    public void setInr_amt(BigDecimal inr_amt) {
        this.inr_amt = inr_amt;
    }

    public BigDecimal getUsd_num() {
        return usd_num;
    }

    public void setUsd_num(BigDecimal usd_num) {
        this.usd_num = usd_num;
    }

    public BigDecimal getInr_num() {
        return inr_num;
    }

    public void setInr_num(BigDecimal inr_num) {
        this.inr_num = inr_num;
    }
}
