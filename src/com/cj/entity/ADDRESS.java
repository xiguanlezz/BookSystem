package com.cj.entity;

public class ADDRESS {
    private int ADDRESS_ID;
    private String ADDRESS_U_ID;
    private String ADDRESS_U_NAME;
    private String ADDRESS_U_MOBILE;
    private String ADDRESS_PROVINCE;
    private String ADDRESS_CITY;
    private String ADDRESS_DETAILED_LOCATION;
    private String ADDRESS_ZIPCODE;

    public ADDRESS() {
    }

    public ADDRESS(int ADDRESS_ID, String ADDRESS_U_ID, String ADDRESS_U_NAME, String ADDRESS_U_MOBILE, String ADDRESS_PROVINCE, String ADDRESS_CITY, String ADDRESS_DETAILED_LOCATION, String ADDRESS_ZIPCODE) {
        this.ADDRESS_ID = ADDRESS_ID;
        this.ADDRESS_U_ID = ADDRESS_U_ID;
        this.ADDRESS_U_NAME = ADDRESS_U_NAME;
        this.ADDRESS_U_MOBILE = ADDRESS_U_MOBILE;
        this.ADDRESS_PROVINCE = ADDRESS_PROVINCE;
        this.ADDRESS_CITY = ADDRESS_CITY;
        this.ADDRESS_DETAILED_LOCATION = ADDRESS_DETAILED_LOCATION;
        this.ADDRESS_ZIPCODE = ADDRESS_ZIPCODE;
    }

    @Override
    public String toString() {
        return "ADDRESS{" +
                "ADDRESS_ID=" + ADDRESS_ID +
                ", ADDRESS_U_ID='" + ADDRESS_U_ID + '\'' +
                ", ADDRESS_U_NAME='" + ADDRESS_U_NAME + '\'' +
                ", ADDRESS_U_MOBILE='" + ADDRESS_U_MOBILE + '\'' +
                ", ADDRESS_PROVINCE='" + ADDRESS_PROVINCE + '\'' +
                ", ADDRESS_CITY='" + ADDRESS_CITY + '\'' +
                ", ADDRESS_DETAILED_LOCATION='" + ADDRESS_DETAILED_LOCATION + '\'' +
                ", ADDRESS_ZIPCODE='" + ADDRESS_ZIPCODE + '\'' +
                '}';
    }

    public int getADDRESS_ID() {
        return ADDRESS_ID;
    }

    public void setADDRESS_ID(int ADDRESS_ID) {
        this.ADDRESS_ID = ADDRESS_ID;
    }

    public String getADDRESS_U_ID() {
        return ADDRESS_U_ID;
    }

    public void setADDRESS_U_ID(String ADDRESS_U_ID) {
        this.ADDRESS_U_ID = ADDRESS_U_ID;
    }

    public String getADDRESS_U_NAME() {
        return ADDRESS_U_NAME;
    }

    public void setADDRESS_U_NAME(String ADDRESS_U_NAME) {
        this.ADDRESS_U_NAME = ADDRESS_U_NAME;
    }

    public String getADDRESS_U_MOBILE() {
        return ADDRESS_U_MOBILE;
    }

    public void setADDRESS_U_MOBILE(String ADDRESS_U_MOBILE) {
        this.ADDRESS_U_MOBILE = ADDRESS_U_MOBILE;
    }

    public String getADDRESS_PROVINCE() {
        return ADDRESS_PROVINCE;
    }

    public void setADDRESS_PROVINCE(String ADDRESS_PROVINCE) {
        this.ADDRESS_PROVINCE = ADDRESS_PROVINCE;
    }

    public String getADDRESS_CITY() {
        return ADDRESS_CITY;
    }

    public void setADDRESS_CITY(String ADDRESS_CITY) {
        this.ADDRESS_CITY = ADDRESS_CITY;
    }

    public String getADDRESS_DETAILED_LOCATION() {
        return ADDRESS_DETAILED_LOCATION;
    }

    public void setADDRESS_DETAILED_LOCATION(String ADDRESS_DETAILED_LOCATION) {
        this.ADDRESS_DETAILED_LOCATION = ADDRESS_DETAILED_LOCATION;
    }

    public String getADDRESS_ZIPCODE() {
        return ADDRESS_ZIPCODE;
    }

    public void setADDRESS_ZIPCODE(String ADDRESS_ZIPCODE) {
        this.ADDRESS_ZIPCODE = ADDRESS_ZIPCODE;
    }
}
