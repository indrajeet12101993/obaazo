package com.ansh.obaazo.model;

import com.google.gson.annotations.SerializedName;

public class UserRequest {
    @SerializedName("user_id")
    private String userId;
    @SerializedName("name")
    private String name;
    @SerializedName("mobile")
    private String mobile;
    private String email;
    @SerializedName("guest_time")
    private String guestTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGuestTime() {
        return guestTime;
    }

    public void setGuestTime(String guestTime) {
        this.guestTime = guestTime;
    }
}
