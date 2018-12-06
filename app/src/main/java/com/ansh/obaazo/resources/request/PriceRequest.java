package com.ansh.obaazo.resources.request;

import com.ansh.obaazo.web.ApiRequest;

import java.util.ArrayList;

public class PriceRequest extends ApiRequest {

    /**
     * checkInDate : 11/20/2018
     * checkOutDate : 11/21/2018
     * roomId : 300
     * roomDetails : [{"noOfAdult":2,"noOfChild":1},{"noOfAdult":1,"noOfChild":1}]
     */

    private String checkInDate;
    private String checkOutDate;
    private String roomId;
    private ArrayList<RoomDetailsBean> roomDetails;

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public ArrayList<RoomDetailsBean> getRoomDetails() {
        return roomDetails;
    }

    public void setRoomDetails(ArrayList<RoomDetailsBean> roomDetails) {
        this.roomDetails = roomDetails;
    }

    public static class RoomDetailsBean {
        /**
         * noOfAdult : 2
         * noOfChild : 1
         */

        private int noOfAdult;
        private int noOfChild;

        public int getNoOfAdult() {
            return noOfAdult;
        }

        public void setNoOfAdult(int noOfAdult) {
            this.noOfAdult = noOfAdult;
        }

        public int getNoOfChild() {
            return noOfChild;
        }

        public void setNoOfChild(int noOfChild) {
            this.noOfChild = noOfChild;
        }
    }
}
