package com.cj.entity;

public class USER {
    private String USER_ID;
    private String USER_NAME;
    private String USER_PASSWORD;
    private String USER_SEX;
    private String USER_BIRTHDAY;
    private String USER_IDENTITY_CODE;
    private String USER_EMAIL;
    private String USER_MOBILE;
    private String USER_ADDRESS;
    private int USER_STATUS;

    public USER() {
    }

    public USER(String USER_ID, String USER_NAME, String USER_PASSWORD, String USER_SEX, String USER_BIRTHDAY, String USER_IDENTITY_CODE, String USER_EMAIL, String USER_MOBILE, String USER_ADDRESS, int USER_STATUS) {
        this.USER_ID = USER_ID;
        this.USER_NAME = USER_NAME;
        this.USER_PASSWORD = USER_PASSWORD;
        this.USER_SEX = USER_SEX;
        this.USER_BIRTHDAY = USER_BIRTHDAY;
        this.USER_IDENTITY_CODE = USER_IDENTITY_CODE;
        this.USER_EMAIL = USER_EMAIL;
        this.USER_MOBILE = USER_MOBILE;
        this.USER_ADDRESS = USER_ADDRESS;
        this.USER_STATUS = USER_STATUS;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getUSER_PASSWORD() {
        return USER_PASSWORD;
    }

    public void setUSER_PASSWORD(String USER_PASSWORD) {
        this.USER_PASSWORD = USER_PASSWORD;
    }

    public String getUSER_SEX() {
        return USER_SEX;
    }

    public void setUSER_SEX(String USER_SEX) {
        this.USER_SEX = USER_SEX;
    }

    public String getUSER_BIRTHDAY() {
        return USER_BIRTHDAY;
    }

    public void setUSER_BIRTHDAY(String USER_BIRTHDAY) {
        this.USER_BIRTHDAY = USER_BIRTHDAY;
    }

    public String getUSER_IDENTITY_CODE() {
        return USER_IDENTITY_CODE;
    }

    public void setUSER_IDENTITY_CODE(String USER_IDENTITY_CODE) {
        this.USER_IDENTITY_CODE = USER_IDENTITY_CODE;
    }

    public String getUSER_EMAIL() {
        return USER_EMAIL;
    }

    public void setUSER_EMAIL(String USER_EMAIL) {
        this.USER_EMAIL = USER_EMAIL;
    }

    public String getUSER_MOBILE() {
        return USER_MOBILE;
    }

    public void setUSER_MOBILE(String USER_MOBILE) {
        this.USER_MOBILE = USER_MOBILE;
    }

    public String getUSER_ADDRESS() {
        return USER_ADDRESS;
    }

    public void setUSER_ADDRESS(String USER_ADDRESS) {
        this.USER_ADDRESS = USER_ADDRESS;
    }

    public int getUSER_STATUS() {
        return USER_STATUS;
    }

    public void setUSER_STATUS(int USER_STATUS) {
        this.USER_STATUS = USER_STATUS;
    }

    @Override
    public String toString() {
        return "USER{" +
                "USER_ID='" + USER_ID + '\'' +
                ", USER_NAME='" + USER_NAME + '\'' +
                ", USER_PASSWORD='" + USER_PASSWORD + '\'' +
                ", USER_SEX='" + USER_SEX + '\'' +
                ", USER_BIRTHDAY='" + USER_BIRTHDAY + '\'' +
                ", USER_IDENTITY_CODE='" + USER_IDENTITY_CODE + '\'' +
                ", USER_EMAIL='" + USER_EMAIL + '\'' +
                ", USER_MOBILE='" + USER_MOBILE + '\'' +
                ", USER_ADDRESS='" + USER_ADDRESS + '\'' +
                ", USER_STATUS=" + USER_STATUS +
                '}';
    }
}
