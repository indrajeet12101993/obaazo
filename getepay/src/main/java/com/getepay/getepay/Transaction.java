package com.getepay.getepay;

import java.io.Serializable;

public class Transaction implements Serializable
{

    private String surcharge;

    private String orderNumber;

    private String agentName;

    private String merchantOrderNo;

    private String transactionId;

    private String modifiedDate;

    private String txnDatetime;

    private String status;

    private String agentId;

    private String message;

    private String createdDate;

    private String txnAmount;

    public String getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(String surcharge) {
        this.surcharge = surcharge;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getTxnDatetime() {
        return txnDatetime;
    }

    public void setTxnDatetime(String txnDatetime) {
        this.txnDatetime = txnDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getTxnAmount() {
        return txnAmount;
    }

    public void setTxnAmount(String txnAmount) {
        this.txnAmount = txnAmount;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [ surcharge = "+surcharge+",  orderNumber = "+orderNumber+", agentName = "+agentName+", merchantOrderNo = "+merchantOrderNo+", transactionId = "+transactionId+", modifiedDate = "+modifiedDate+", txnDatetime = "+txnDatetime+", status = "+status+", agentId = "+agentId+", message = "+message+", createdDate = "+createdDate+", txnAmount = "+txnAmount+"]";
    }
}
