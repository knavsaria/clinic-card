package com.navsaria.keeran.clinicbook;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by keeran on 2017/11/23.
 */

public class Child implements Serializable {

    private UUID mId;

    private String mIdNumber;
    private String mFirstName;
    private String mLastName;
    private boolean mIsBoy;
    private Date mDob;
    private Parent mMother;
    private Parent mFather;
    private String mBirthFacility;
    private String mChildStayingWith;
    private String mAddress;

    public String getBirthFacility() {
        return mBirthFacility;
    }

    public void setBirthFacility(String birthFacility) {
        mBirthFacility = birthFacility;
    }

    public String getChildStayingWith() {
        return mChildStayingWith;
    }

    public void setChildStayingWith(String childStayingWith) {
        mChildStayingWith = childStayingWith;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public Parent getMother() {
        return mMother;
    }

    public void setMother(Parent mother) {
        mMother = mother;
    }

    public Parent getFather() {
        return mFather;
    }

    public void setFather(Parent father) {
        mFather = father;
    }

    public String getIdNumber() {
        return mIdNumber;
    }

    public void setIdNumber(String idNumber) {
        mIdNumber = idNumber;
    }

    public Date getDob() {
        return mDob;
    }

    public void setDob(Date dob) {
        mDob = dob;
    }

    public Child() {
        this(UUID.randomUUID());
        mDob = new Date();
    }

    public Child(UUID id) {
        mId = id;
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

    public boolean isBoy() {
        return mIsBoy;
    }

    public void setBoy(boolean boy) {
        mIsBoy = boy;
    }

    public UUID getId() {
        return mId;
    }
}
