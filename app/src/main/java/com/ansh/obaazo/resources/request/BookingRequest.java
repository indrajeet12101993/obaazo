package com.ansh.obaazo.resources.request;

import com.ansh.obaazo.model.AmountRequest;
import com.ansh.obaazo.model.DiscountRequest;
import com.ansh.obaazo.model.RoomDetailRequest;
import com.ansh.obaazo.model.UserRequest;
import com.ansh.obaazo.web.ApiRequest;
import com.getepay.getepay.PaymentResult;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookingRequest extends ApiRequest {

    @SerializedName("user_detail")
    private UserRequest request;
    @SerializedName("amount")
    private AmountRequest amountRequest;
    @SerializedName("room_detail")
    private ArrayList<RoomDetailRequest> roomDetails;
    @SerializedName("discount")
    private DiscountRequest discountRequest;
    private PaymentResult paymentResult;

    public UserRequest getRequest() {
        return request;
    }

    public void setRequest(UserRequest request) {
        this.request = request;
    }

    public AmountRequest getAmountRequest() {
        return amountRequest;
    }

    public void setAmountRequest(AmountRequest amountRequest) {
        this.amountRequest = amountRequest;
    }

    public ArrayList<RoomDetailRequest> getRoomDetails() {
        return roomDetails;
    }

    public void setRoomDetails(ArrayList<RoomDetailRequest> roomDetails) {
        this.roomDetails = roomDetails;
    }

    public DiscountRequest getDiscountRequest() {
        return discountRequest;
    }

    public void setDiscountRequest(DiscountRequest discountRequest) {
        this.discountRequest = discountRequest;
    }


    public void setPaymentResult(PaymentResult paymentResult) {
        this.paymentResult = paymentResult;
    }
}
