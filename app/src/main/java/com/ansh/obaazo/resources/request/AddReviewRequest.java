package com.ansh.obaazo.resources.request;

import com.ansh.obaazo.web.ApiRequest;

/**
 * Created by Jeevan Gupta on 11/17/2018.
 */
public class AddReviewRequest extends ApiRequest {
    private String userId;
    private String userName;
    private String hotelId;
    private String hotelName;
    private String hotelRating;
    private String comment;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelRating() {
        return hotelRating;
    }

    public void setHotelRating(String hotelRating) {
        this.hotelRating = hotelRating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
