package com.example.e_smarcel;

    public class Recipient {

        private String trackNum, name, numPhone, ParcelStat;

        public Recipient(){}

        public Recipient(String trackNum, String name, String numPhone, String parcelStat) {
            this.trackNum = trackNum;
            this.name = name;
            this.numPhone = numPhone;
            ParcelStat = parcelStat;
        }

        public String getTrackNum() {
            return trackNum;
        }

        public void setTrackNum(String trackNum) {
            this.trackNum = trackNum;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumPhone() {
            return numPhone;
        }

        public void setNumPhone(String numPhone) {
            this.numPhone = numPhone;
        }

        public String getParcelStat() {
            return ParcelStat;
        }

        public void setParcelStat(String parcelStat) {
            ParcelStat = parcelStat;
        }
    }
