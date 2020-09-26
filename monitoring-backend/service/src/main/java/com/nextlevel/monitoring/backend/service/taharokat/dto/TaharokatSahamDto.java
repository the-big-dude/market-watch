package com.nextlevel.monitoring.backend.service.taharokat.dto;

import java.util.ArrayList;
import java.util.List;

public class TaharokatSahamDto {

    String sahmName;
    List<Float> kharidarBForooshandehList;
    List<Float> saranehKharidList;
    int jumpNesbat;
    int jumpSaraneh;
    float plusD;
    float plusN;
    float minusN;
    float m;
    float mBd;

    public TaharokatSahamDto() {
        kharidarBForooshandehList = new ArrayList<>();
        saranehKharidList = new ArrayList<>();
    }

    public String getSahmName() {
        return sahmName;
    }

    public void setSahmName(String sahmName) {
        this.sahmName = sahmName;
    }

    public List<Float> getKharidarBForooshandehList() {
        return kharidarBForooshandehList;
    }

    public void setKharidarBForooshandehList(List<Float> kharidarBForooshandehList) {
        this.kharidarBForooshandehList = kharidarBForooshandehList;
    }

    public List<Float> getSaranehKharidList() {
        return saranehKharidList;
    }

    public void setSaranehKharidList(List<Float> saranehKharidList) {
        this.saranehKharidList = saranehKharidList;
    }

    public int getJumpNesbat() {
        return jumpNesbat;
    }

    public void setJumpNesbat(int jumpNesbat) {
        this.jumpNesbat = jumpNesbat;
    }

    public int getJumpSaraneh() {
        return jumpSaraneh;
    }

    public void setJumpSaraneh(int jumpSaraneh) {
        this.jumpSaraneh = jumpSaraneh;
    }

    public float getPlusD() {
        return plusD;
    }

    public void setPlusD(float plusD) {
        this.plusD = plusD;
    }

    public float getPlusN() {
        return plusN;
    }

    public void setPlusN(float plusN) {
        this.plusN = plusN;
    }

    public float getMinusN() {
        return minusN;
    }

    public void setMinusN(float minusN) {
        this.minusN = minusN;
    }

    public float getM() {
        return m;
    }

    public void setM(float m) {
        this.m = m;
    }

    public float getmBd() {
        return mBd;
    }

    public void setmBd(float mBd) {
        this.mBd = mBd;
    }
}
