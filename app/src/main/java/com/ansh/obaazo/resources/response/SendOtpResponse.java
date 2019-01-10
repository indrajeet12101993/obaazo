package com.ansh.obaazo.resources.response;

import com.ansh.obaazo.web.ApiResponse;
import com.google.gson.annotations.SerializedName;

public class SendOtpResponse extends ApiResponse {

    /**
     * response_code : 200
     * response_message : success
     */

    private String response_code;
    private String response_message;
    @SerializedName("Usertype")
    private String customerType;
    @SerializedName("result")
    private UserDetails userDetails;


    public String getResponse_code() {
        return response_code;

    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public void setResponse_code(String response_code) {
        this.response_code = response_code;
    }

    public String getResponse_message() {
        return response_message;
    }

    public void setResponse_message(String response_message) {
        this.response_message = response_message;
    }


    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public class UserDetails {


        /**
         * id : 155
         * oauth_provider :
         * oauth_uid :
         * name :
         * last_name :
         * email : techzesta@gmail.com
         * mobile :
         * gender :
         * locale :
         * picture_url :
         * profile_url :
         * created : 09-01-2019
         * modified : 0000-00-00 00:00:00
         * otp : 0
         * social_login : 0
         * gstid :
         * company_name :
         * company_address :
         * picture :
         */

        private String id;
        private String name;
        private String last_name;
        private String email;
        private String mobile;
        private String gender;
        private String locale;
        private String picture_url;
        private String profile_url;
        private String created;
        private String social_login;
        private String gstid;
        private String company_name;
        private String company_address;
        private String picture;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getLocale() {
            return locale;
        }

        public void setLocale(String locale) {
            this.locale = locale;
        }

        public String getPicture_url() {
            return picture_url;
        }

        public void setPicture_url(String picture_url) {
            this.picture_url = picture_url;
        }

        public String getProfile_url() {
            return profile_url;
        }

        public void setProfile_url(String profile_url) {
            this.profile_url = profile_url;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }


        public String getSocial_login() {
            return social_login;
        }

        public void setSocial_login(String social_login) {
            this.social_login = social_login;
        }

        public String getGstid() {
            return gstid;
        }

        public void setGstid(String gstid) {
            this.gstid = gstid;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getCompany_address() {
            return company_address;
        }

        public void setCompany_address(String company_address) {
            this.company_address = company_address;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }

}
