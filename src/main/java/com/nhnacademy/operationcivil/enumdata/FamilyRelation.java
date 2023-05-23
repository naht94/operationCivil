package com.nhnacademy.operationcivil.enumdata;

public enum FamilyRelation {
    father("부"),
    mother("모"),
    spouse("배우자"),
    child("자녀");
    private String korean;
    FamilyRelation(String korean) {
        this.korean = korean;
    }
    public String getKorean() {
        return korean;
    }
}
