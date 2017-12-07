package com.navsaria.keeran.clinicbook;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by keeran on 2017/12/05.
 */

public class Parent implements Serializable {

    private UUID mId;
    private String mFirstName;
    private String mLastName;
    private String mIdNumber;
    private int mNoOfBirths;
    private int mNoOfAlive;
   // private String[] mmReasonsForDeaths;
    private boolean mIsADad;
    private Date mDob;

    public Parent() {
        this(UUID.randomUUID());
        mDob = new Date();
        mNoOfBirths = 0;
        mNoOfAlive = 0;
    }

    public Parent(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

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

    public String getIdNumber() {
        return mIdNumber;
    }

    public void setIdNumber(String idNumber) {
        mIdNumber = idNumber;
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

//    public String[] getMmReasonsForDeaths() {
//        return mmReasonsForDeaths;
//    }
//
//    public void setMmReasonsForDeaths(String[] mmReasonsForDeaths) {
//        this.mmReasonsForDeaths = mmReasonsForDeaths;
//    }

    public boolean isADad() {
        return mIsADad;
    }

    public void setADad(boolean aDad) {
        mIsADad = aDad;
    }

    public Date getDob() {
        return mDob;
    }

    public void setDob(Date dob) {
        mDob = dob;
    }


}
