package com.getepay.getepay;

import java.io.Serializable;

public class Merchant implements Serializable
{
    private String country;

    private String pincode;

    private String rightLogo;

    private String address;

    private String city;

    private String mobileNumber;

    private String mid;

    private String leftLogo;

    private String userId;

    private String merchantName;

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    public String getPincode ()
    {
        return pincode;
    }

    public void setPincode (String pincode)
    {
        this.pincode = pincode;
    }

    public String getRightLogo ()
    {
        return rightLogo;
    }

    public void setRightLogo (String rightLogo)
    {
        this.rightLogo = rightLogo;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getMobileNumber ()
    {
        return mobileNumber;
    }

    public void setMobileNumber (String mobileNumber)
    {
        this.mobileNumber = mobileNumber;
    }

    public String getMid ()
    {
        return mid;
    }

    public void setMid (String mid)
    {
        this.mid = mid;
    }

    public String getLeftLogo ()
    {
        return leftLogo;
    }

    public void setLeftLogo (String leftLogo)
    {
        this.leftLogo = leftLogo;
    }

    public String getUserId ()
    {
        return userId;
    }

    public void setUserId (String userId)
    {
        this.userId = userId;
    }

    public String getMerchantName ()
    {
        return merchantName;
    }

    public void setMerchantName (String merchantName)
    {
        this.merchantName = merchantName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [country = "+country+", pincode = "+pincode+", rightLogo = "+rightLogo+", address = "+address+", city = "+city+", mobileNumber = "+mobileNumber+", mid = "+mid+", leftLogo = "+leftLogo+", userId = "+userId+", merchantName = "+merchantName+"]";
    }
}
