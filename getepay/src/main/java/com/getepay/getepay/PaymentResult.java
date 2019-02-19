package com.getepay.getepay;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Belal on 10/2/2017.
 */


public class PaymentResult implements Serializable
{
    @SerializedName("transaction")
    private Transaction transaction;

    @SerializedName("merchant")
    private Merchant merchant;


    @SerializedName("message")
    private String message;


    @SerializedName("status")
    private String status;


    public PaymentResult(String status, String message,Merchant merchant,Transaction transaction) {
        this.status = status;
        this.message = message;
        this.transaction=transaction;
        this.merchant=merchant;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [merchant = "+merchant+", message = "+message+", transaction = "+transaction+", status = "+status+"]";
    }
}