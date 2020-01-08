package com.cj.entity;

public class CATEGORY {
    private int CATE_ID;
    private String CATE_NAME;
    private int CATE_PARENT_ID;

    public CATEGORY() {
    }

    public CATEGORY(int CATE_ID, String CATE_NAME, int CATE_PARENT_ID) {
        this.CATE_ID = CATE_ID;
        this.CATE_NAME = CATE_NAME;
        this.CATE_PARENT_ID = CATE_PARENT_ID;
    }

    public int getCATE_ID() {
        return CATE_ID;
    }

    public void setCATE_ID(int CATE_ID) {
        this.CATE_ID = CATE_ID;
    }

    public String getCATE_NAME() {
        return CATE_NAME;
    }

    public void setCATE_NAME(String CATE_NAME) {
        this.CATE_NAME = CATE_NAME;
    }

    public int getCATE_PARENT_ID() {
        return CATE_PARENT_ID;
    }

    public void setCATE_PARENT_ID(int CATE_PARENT_ID) {
        this.CATE_PARENT_ID = CATE_PARENT_ID;
    }

    @Override
    public String toString() {
        return "CATEGORY{" +
                "CATE_ID=" + CATE_ID +
                ", CATE_NAME='" + CATE_NAME + '\'' +
                ", CATE_PARENT_ID=" + CATE_PARENT_ID +
                '}';
    }
}
