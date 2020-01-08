package com.cj.entity;

public class CART {
    private int CART_ID;
    private String CART_P_FILENAME;
    private String CART_P_NAME;
    private int CART_P_PRICE;
    private int CART_QUANTITY;
    private int CART_P_STOCK;
    private int CART_P_ID;
    private String CART_U_ID;
    private int CART_VALID;

    public CART() {
    }

    @Override
    public String toString() {
        return "CART{" +
                "CART_ID=" + CART_ID +
                ", CART_P_FILENAME='" + CART_P_FILENAME + '\'' +
                ", CART_P_NAME='" + CART_P_NAME + '\'' +
                ", CART_P_PRICE=" + CART_P_PRICE +
                ", CART_QUANTITY=" + CART_QUANTITY +
                ", CART_P_STOCK=" + CART_P_STOCK +
                ", CART_P_ID=" + CART_P_ID +
                ", CART_U_ID='" + CART_U_ID + '\'' +
                ", CART_VALID=" + CART_VALID +
                '}';
    }

    public int getCART_ID() {
        return CART_ID;
    }

    public void setCART_ID(int CART_ID) {
        this.CART_ID = CART_ID;
    }

    public String getCART_P_FILENAME() {
        return CART_P_FILENAME;
    }

    public void setCART_P_FILENAME(String CART_P_FILENAME) {
        this.CART_P_FILENAME = CART_P_FILENAME;
    }

    public String getCART_P_NAME() {
        return CART_P_NAME;
    }

    public void setCART_P_NAME(String CART_P_NAME) {
        this.CART_P_NAME = CART_P_NAME;
    }

    public int getCART_P_PRICE() {
        return CART_P_PRICE;
    }

    public void setCART_P_PRICE(int CART_P_PRICE) {
        this.CART_P_PRICE = CART_P_PRICE;
    }

    public int getCART_QUANTITY() {
        return CART_QUANTITY;
    }

    public void setCART_QUANTITY(int CART_QUANTITY) {
        this.CART_QUANTITY = CART_QUANTITY;
    }

    public int getCART_P_STOCK() {
        return CART_P_STOCK;
    }

    public void setCART_P_STOCK(int CART_P_STOCK) {
        this.CART_P_STOCK = CART_P_STOCK;
    }

    public int getCART_P_ID() {
        return CART_P_ID;
    }

    public void setCART_P_ID(int CART_P_ID) {
        this.CART_P_ID = CART_P_ID;
    }

    public String getCART_U_ID() {
        return CART_U_ID;
    }

    public void setCART_U_ID(String CART_U_ID) {
        this.CART_U_ID = CART_U_ID;
    }

    public int getCART_VALID() {
        return CART_VALID;
    }

    public void setCART_VALID(int CART_VALID) {
        this.CART_VALID = CART_VALID;
    }

    public CART(int CART_ID, String CART_P_FILENAME, String CART_P_NAME, int CART_P_PRICE, int CART_QUANTITY, int CART_P_STOCK, int CART_P_ID, String CART_U_ID, int CART_VALID) {
        this.CART_ID = CART_ID;
        this.CART_P_FILENAME = CART_P_FILENAME;
        this.CART_P_NAME = CART_P_NAME;
        this.CART_P_PRICE = CART_P_PRICE;
        this.CART_QUANTITY = CART_QUANTITY;
        this.CART_P_STOCK = CART_P_STOCK;
        this.CART_P_ID = CART_P_ID;
        this.CART_U_ID = CART_U_ID;
        this.CART_VALID = CART_VALID;
    }
}
