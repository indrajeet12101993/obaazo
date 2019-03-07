package com.ansh.obaazo.model;

import com.google.gson.annotations.SerializedName;

public class AmountRequest {
    @SerializedName("final_amount")
    private String finalAmount;
    @SerializedName("booking_amount")
    private String bookingAmount;
    @SerializedName("payment_option")
    private String paymentOption;
    @SerializedName("overallgst")
    private String overAllGst;
    @SerializedName("checkin")
    private String checkIndate;
    @SerializedName("checkout")
    private String checkOutDate;
    private String transactionNo;


    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public String getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(String finalAmount) {
        this.finalAmount = finalAmount;
    }

    public String getBookingAmount() {
        return bookingAmount;
    }

    public void setBookingAmount(String bookingAmount) {
        this.bookingAmount = bookingAmount;
    }

    public String getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(String paymentOption) {
        this.paymentOption = paymentOption;
    }

    public String getOverAllGst() {
        return overAllGst;
    }

    public void setOverAllGst(String overAllGst) {
        this.overAllGst = overAllGst;
    }

    public String getCheckIndate() {
        return checkIndate;
    }

    public void setCheckIndate(String checkIndate) {
        this.checkIndate = checkIndate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
