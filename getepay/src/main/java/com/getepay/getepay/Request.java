package com.getepay.getepay;

import java.io.Serializable;

public class Request implements Serializable
{
    private String agentId ;
    private String merchantOrderNo;
    private String txnAmount ;
    private String agentName ;
    private String udf1 ;
    private String udf2 ;
    private String udf3 ;
    private String udf4 ;
    private String udf5 ;
    private String txndatetime ;
    private String password;
      //private String ru;

    public Request(){

    }
    public Request(String agentId, String merchantOrderNo, String txnAmount, String agentName, String udf1, String udf2, String udf3, String udf4, String udf5, String txndatetime, String password)
    {
        this.agentId = agentId;
        this.merchantOrderNo = merchantOrderNo;
        this.txnAmount = txnAmount;
        this.agentName = agentName;
        this.udf1 = udf1;
        this.udf2 = udf2;
        this.udf3 = udf3;
        this.udf4 = udf4;
        this.udf5 = udf5;
        this.txndatetime = txndatetime;
        this.password = password;

    }


    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public String getTxnAmount() {
        return txnAmount;
    }

    public void setTxnAmount(String txnAmount) {
        this.txnAmount = txnAmount;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getUdf1() {
        return udf1;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public String getUdf4() {
        return udf4;
    }

    public void setUdf4(String udf4) {
        this.udf4 = udf4;
    }

    public String getUdf5() {
        return udf5;
    }

    public void setUdf5(String udf5) {
        this.udf5 = udf5;
    }

    public String getTxndatetime() {
        return txndatetime;
    }

    public void setTxndatetime(String txndatetime) {
        this.txndatetime = txndatetime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   }
