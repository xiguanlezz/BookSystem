package com.cj.entity;

public class EVALUATION {
    private int EVALUATION_ID;
    private String EVALUATION_U_ID;
    private int EVALUATION_O_ID;
    private int EVALUATION_SCORE;
    private String EVALUATION_WORDS;

    public EVALUATION() {
    }

    public EVALUATION(int EVALUATION_ID, String EVALUATION_U_ID, int EVALUATION_O_ID, int EVALUATION_SCORE, String EVALUATION_WORDS) {
        this.EVALUATION_ID = EVALUATION_ID;
        this.EVALUATION_U_ID = EVALUATION_U_ID;
        this.EVALUATION_O_ID = EVALUATION_O_ID;
        this.EVALUATION_SCORE = EVALUATION_SCORE;
        this.EVALUATION_WORDS = EVALUATION_WORDS;
    }

    @Override
    public String toString() {
        return "EVALUATION{" +
                "EVALUATION_ID=" + EVALUATION_ID +
                ", EVALUATION_U_ID='" + EVALUATION_U_ID + '\'' +
                ", EVALUATION_O_ID=" + EVALUATION_O_ID +
                ", EVALUATION_SCORE=" + EVALUATION_SCORE +
                ", EVALUATION_WORDS='" + EVALUATION_WORDS + '\'' +
                '}';
    }

    public int getEVALUATION_ID() {
        return EVALUATION_ID;
    }

    public void setEVALUATION_ID(int EVALUATION_ID) {
        this.EVALUATION_ID = EVALUATION_ID;
    }

    public String getEVALUATION_U_ID() {
        return EVALUATION_U_ID;
    }

    public void setEVALUATION_U_ID(String EVALUATION_U_ID) {
        this.EVALUATION_U_ID = EVALUATION_U_ID;
    }

    public int getEVALUATION_O_ID() {
        return EVALUATION_O_ID;
    }

    public void setEVALUATION_O_ID(int EVALUATION_O_ID) {
        this.EVALUATION_O_ID = EVALUATION_O_ID;
    }

    public int getEVALUATION_SCORE() {
        return EVALUATION_SCORE;
    }

    public void setEVALUATION_SCORE(int EVALUATION_SCORE) {
        this.EVALUATION_SCORE = EVALUATION_SCORE;
    }

    public String getEVALUATION_WORDS() {
        return EVALUATION_WORDS;
    }

    public void setEVALUATION_WORDS(String EVALUATION_WORDS) {
        this.EVALUATION_WORDS = EVALUATION_WORDS;
    }
}
