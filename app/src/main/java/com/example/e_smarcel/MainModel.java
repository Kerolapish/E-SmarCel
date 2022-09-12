package com.example.e_smarcel;

public class MainModel {

    //declare variable
    String trackNum, name, numPhone, parcelStatus,dateClaimed;

    MainModel(){
    }

    //constructor for mainModel
    public MainModel(String trackNum, String name, String numPhone, String parcelStatus, String dateClaimed) {
        this.trackNum = trackNum;
        this.name = name;
        this.numPhone = numPhone;
        this.parcelStatus = parcelStatus;
        this.dateClaimed = dateClaimed;
    }

    //getter and setter for trackNum
    public String getTrackNum() {

        return trackNum;
    }

    public void setTrackNum(String trackNum) {

        this.trackNum = trackNum;
    }

    //getter and setter for name
    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //getter and setter for phoneNum
    public String getNumPhone() {

        return numPhone;
    }

    public void setNumPhone(String numPhone) {

        this.numPhone = numPhone;
    }

    //getter and setter for parcelStatus
    public String getParcelStatus() {

        return parcelStatus;
    }

    public void setParcelStatus(String parcelStatus) {

        this.parcelStatus = parcelStatus;
    }

    //getter and setter for dateClaimed
    public String getDateClaimed() {

        return dateClaimed;
    }

    public void setDateClaimed(String dateClaimed) {

        this.dateClaimed = dateClaimed;
    }
}

