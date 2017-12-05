package com.navsaria.keeran.clinicbook;

import java.util.Date;

/**
 * Created by keeran on 2017/12/05.
 */

public class Parent {
    private String mFirstName;
    private String mLastName;
    private String mId;
    private int mNoOfBirths;
    private int mNoOfAlive;
    private String[] mmReasonsForDeaths;
    private boolean mIsAMom;
    private Date mDob;

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public int getNoOfBirths() {
        return mNoOfBirths;
    }

    public void setNoOfBirths(int noOfBirths) {
        mNoOfBirths = noOfBirths;
    }

    public int getNoOfAlive() {
        return mNoOfAlive;
    }

    public void setNoOfAlive(int noOfAlive) {
        mNoOfAlive = noOfAlive;
    }

    public String[] getMmReasonsForDeaths() {
        return mmReasonsForDeaths;
    }

    public void setMmReasonsForDeaths(String[] mmReasonsForDeaths) {
        this.mmReasonsForDeaths = mmReasonsForDeaths;
    }

    public boolean isAMom() {
        return mIsAMom;
    }

    public void setAMom(boolean AMom) {
        mIsAMom = AMom;
    }

    public Date getDob() {
        return mDob;
    }

    public void setDob(Date dob) {
        mDob = dob;
    }


}
