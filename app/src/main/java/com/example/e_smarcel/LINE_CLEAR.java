package com.example.e_smarcel;

public class LINE_CLEAR {

    String trackNum, specKey, date;

    public LINE_CLEAR(String trackNum, String specKey, String date) {
        this.trackNum = trackNum;
        this.specKey = specKey;
        this.date = date;
    }

    public String getTrackNum() {

        return trackNum;
    }

    public void setTrackNum(String trackNum) {

        this.trackNum = trackNum;
    }

    public String getSpecKey() {

        return specKey;
    }

    public void setSpecKey(String specKey) {

        this.specKey = specKey;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {

        this.date = date;
    }
}


