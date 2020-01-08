package com.cj.entity;

import java.util.Objects;

public class ITEM {
    private int ITEM_ID;
    private int ITEM_O_ID;
    private int ITEM_P_ID;
    private int ITEM_P_QUANTITY;

    public int getITEM_ID() {
        return ITEM_ID;
    }

    public void setITEM_ID(int ITEM_ID) {
        this.ITEM_ID = ITEM_ID;
    }

    public int getITEM_O_ID() {
        return ITEM_O_ID;
    }

    public void setITEM_O_ID(int ITEM_O_ID) {
        this.ITEM_O_ID = ITEM_O_ID;
    }

    public int getITEM_P_ID() {
        return ITEM_P_ID;
    }

    public void setITEM_P_ID(int ITEM_P_ID) {
        this.ITEM_P_ID = ITEM_P_ID;
    }

    public int getITEM_P_QUANTITY() {
        return ITEM_P_QUANTITY;
    }

    public void setITEM_P_QUANTITY(int ITEM_P_QUANTITY) {
        this.ITEM_P_QUANTITY = ITEM_P_QUANTITY;
    }

    public ITEM() {
    }

    public ITEM(int ITEM_ID, int ITEM_O_ID, int ITEM_P_ID, int ITEM_P_QUANTITY) {
        this.ITEM_ID = ITEM_ID;
        this.ITEM_O_ID = ITEM_O_ID;
        this.ITEM_P_ID = ITEM_P_ID;
        this.ITEM_P_QUANTITY = ITEM_P_QUANTITY;
    }

    @Override
    public String toString() {
        return "ITEM{" +
                "ITEM_ID=" + ITEM_ID +
                ", ITEM_O_ID=" + ITEM_O_ID +
                ", ITEM_P_ID=" + ITEM_P_ID +
                ", ITEM_P_QUANTITY=" + ITEM_P_QUANTITY +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ITEM)) return false;
        ITEM item = (ITEM) o;
        return ITEM_ID == item.ITEM_ID &&
                ITEM_O_ID == item.ITEM_O_ID &&
                ITEM_P_ID == item.ITEM_P_ID &&
                ITEM_P_QUANTITY == item.ITEM_P_QUANTITY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ITEM_ID, ITEM_O_ID, ITEM_P_ID, ITEM_P_QUANTITY);
    }
}
