package com.cj.entity;

public class ORDERS {
    private int ORDER_ID;
    private String ORDER_U_ID;
    private String ORDER_TIME;
    private int ORDER_TOTAL_PRICE;
    private int ORDER_PAY_ID;
    private int ORDER_ADDRESS_ID;
    private String ORDER_REMARK;
    private String ORDER_STATUS;

    public ORDERS() {
    }

    public ORDERS(int ORDER_ID, String ORDER_U_ID, String ORDER_TIME, int ORDER_TOTAL_PRICE, int ORDER_PAY_ID, int ORDER_ADDRESS_ID, String ORDER_REMARK, String ORDER_STATUS) {
        this.ORDER_ID = ORDER_ID;
        this.ORDER_U_ID = ORDER_U_ID;
        this.ORDER_TIME = ORDER_TIME;
        this.ORDER_TOTAL_PRICE = ORDER_TOTAL_PRICE;
        this.ORDER_PAY_ID = ORDER_PAY_ID;
        this.ORDER_ADDRESS_ID = ORDER_ADDRESS_ID;
        this.ORDER_REMARK = ORDER_REMARK;
        this.ORDER_STATUS = ORDER_STATUS;
    }

    @Override
    public String toString() {
        return "ORDERS{" +
                "ORDER_ID=" + ORDER_ID +
                ", ORDER_U_ID='" + ORDER_U_ID + '\'' +
                ", ORDER_TIME='" + ORDER_TIME + '\'' +
                ", ORDER_TOTAL_PRICE=" + ORDER_TOTAL_PRICE +
                ", ORDER_PAY_ID=" + ORDER_PAY_ID +
                ", ORDER_ADDRESS_ID=" + ORDER_ADDRESS_ID +
                ", ORDER_REMARK='" + ORDER_REMARK + '\'' +
                ", ORDER_STATUS='" + ORDER_STATUS + '\'' +
                '}';
    }

    public int getORDER_ID() {
        return ORDER_ID;
    }

    public void setORDER_ID(int ORDER_ID) {
        this.ORDER_ID = ORDER_ID;
    }

    public String getORDER_U_ID() {
        return ORDER_U_ID;
    }

    public void setORDER_U_ID(String ORDER_U_ID) {
        this.ORDER_U_ID = ORDER_U_ID;
    }

    public String getORDER_TIME() {
        return ORDER_TIME;
    }

    public void setORDER_TIME(String ORDER_TIME) {
        this.ORDER_TIME = ORDER_TIME;
    }

    public int getORDER_TOTAL_PRICE() {
        return ORDER_TOTAL_PRICE;
    }

    public void setORDER_TOTAL_PRICE(int ORDER_TOTAL_PRICE) {
        this.ORDER_TOTAL_PRICE = ORDER_TOTAL_PRICE;
    }

    public int getORDER_PAY_ID() {
        return ORDER_PAY_ID;
    }

    public void setORDER_PAY_ID(int ORDER_PAY_ID) {
        this.ORDER_PAY_ID = ORDER_PAY_ID;
    }

    public int getORDER_ADDRESS_ID() {
        return ORDER_ADDRESS_ID;
    }

    public void setORDER_ADDRESS_ID(int ORDER_ADDRESS_ID) {
        this.ORDER_ADDRESS_ID = ORDER_ADDRESS_ID;
    }

    public String getORDER_REMARK() {
        return ORDER_REMARK;
    }

    public void setORDER_REMARK(String ORDER_REMARK) {
        this.ORDER_REMARK = ORDER_REMARK;
    }

    public String getORDER_STATUS() {
        return ORDER_STATUS;
    }

    public void setORDER_STATUS(String ORDER_STATUS) {
        this.ORDER_STATUS = ORDER_STATUS;
    }
}
