package com.cj.entity;

public class PAY {
    private int PAY_ID;
    private String PAY_SEND_START_TIME;
    private String PAY_SEND_END_TIME;
    private String PAY_SEND_TAKE_TIME;
    private String PAY_WAY;
    private String PAY_SEND_WAY;

    public PAY() {
    }

    public PAY(int PAY_ID, String PAY_SEND_START_TIME, String PAY_SEND_END_TIME, String PAY_SEND_TAKE_TIME, String PAY_WAY, String PAY_SEND_WAY) {
        this.PAY_ID = PAY_ID;
        this.PAY_SEND_START_TIME = PAY_SEND_START_TIME;
        this.PAY_SEND_END_TIME = PAY_SEND_END_TIME;
        this.PAY_SEND_TAKE_TIME = PAY_SEND_TAKE_TIME;
        this.PAY_WAY = PAY_WAY;
        this.PAY_SEND_WAY = PAY_SEND_WAY;
    }

    @Override
    public String toString() {
        return "PAY{" +
                "PAY_ID=" + PAY_ID +
                ", PAY_SEND_START_TIME='" + PAY_SEND_START_TIME + '\'' +
                ", PAY_SEND_END_TIME='" + PAY_SEND_END_TIME + '\'' +
                ", PAY_SEND_TAKE_TIME='" + PAY_SEND_TAKE_TIME + '\'' +
                ", PAY_WAY='" + PAY_WAY + '\'' +
                '}';
    }

    public int getPAY_ID() {
        return PAY_ID;
    }

    public void setPAY_ID(int PAY_ID) {
        this.PAY_ID = PAY_ID;
    }

    public String getPAY_SEND_START_TIME() {
        return PAY_SEND_START_TIME;
    }

    public void setPAY_SEND_START_TIME(String PAY_SEND_START_TIME) {
        this.PAY_SEND_START_TIME = PAY_SEND_START_TIME;
    }

    public String getPAY_SEND_END_TIME() {
        return PAY_SEND_END_TIME;
    }

    public void setPAY_SEND_END_TIME(String PAY_SEND_END_TIME) {
        this.PAY_SEND_END_TIME = PAY_SEND_END_TIME;
    }

    public String getPAY_SEND_TAKE_TIME() {
        return PAY_SEND_TAKE_TIME;
    }

    public void setPAY_SEND_TAKE_TIME(String PAY_SEND_TAKE_TIME) {
        this.PAY_SEND_TAKE_TIME = PAY_SEND_TAKE_TIME;
    }

    public String getPAY_WAY() {
        return PAY_WAY;
    }

    public void setPAY_WAY(String PAY_WAY) {
        this.PAY_WAY = PAY_WAY;
    }

    public String getPAY_SEND_WAY() {
        return PAY_SEND_WAY;
    }

    public void setPAY_SEND_WAY(String PAY_SEND_WAY) {
        this.PAY_SEND_WAY = PAY_SEND_WAY;
    }
}
