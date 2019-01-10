package com.ansh.obaazo.resources.response;

import com.ansh.obaazo.web.ApiResponse;

public class ObazoMoneyResponse extends ApiResponse {

    /**
     * response_code : 200
     * response_message : success
     * result : [{"id":"1","user_id":"48","money":"1205","expiry_date":"2020-09-14"}]
     */

    private String response_code;
    private String response_message;
    private ResultBean result;

    public String getResponse_code() {
        return response_code;
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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public class ResultBean {
        /**
         * id : 1
         * user_id : 48
         * money : 1205
         * expiry_date : 2020-09-14
         */

        private String id;
        private String user_id;
        private Double money;
        private String expiry_date;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public Double getMoney() {
            return money;
        }

        public void setMoney(Double money) {
            this.money = money;
        }

        public String getExpiry_date() {
            return expiry_date;
        }

        public void setExpiry_date(String expiry_date) {
            this.expiry_date = expiry_date;
        }
    }
}
