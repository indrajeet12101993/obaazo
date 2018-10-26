package com.ansh.obaazo.model;

import java.util.ArrayList;

public class PersonInfo {
    private Boolean isEdit = false;
    private int noOfAdult = 1;
    private ArrayList<Integer> child = new ArrayList<>();
    public PersonInfo() {
    }

    public int getNoOfAdult() {
        return noOfAdult;
    }

    public void setNoOfAdult(int noOfAdult) {
        this.noOfAdult = noOfAdult;
    }

    public ArrayList<Integer> getChild() {
        return child;
    }

    public void setChild(ArrayList<Integer> child) {
        this.child = child;
    }

    public Boolean getEdit() {
        return isEdit;
    }

    public void setEdit(Boolean edit) {
        isEdit = edit;
    }
}
