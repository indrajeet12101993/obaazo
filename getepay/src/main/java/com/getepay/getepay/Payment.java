package com.getepay.getepay;
public class Payment {

    private String agentId;
    private String merchantOrderNo;

    public Payment(String agentid, String merchantOrderNo) {
        this.agentId = agentid;
        this.merchantOrderNo = merchantOrderNo;
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
}