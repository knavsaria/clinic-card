package com.navsaria.keeran.clinicbook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by keeran on 2017/11/23.
 */

public class Child implements Serializable {

    private UUID mId;
    private UUID mMother;
    private UUID mFather;
    private String mIdNumber;
    private String mFirstName;
    private String mLastName;
    private String mBirthFacility;
    private String mChildStayingWith;
    private String mAddress;
    private boolean mIsBoy;
    private boolean mIsTwin;
    private boolean mIsDisabled;
    private boolean mMotherNeedsSupport;
    private Date mDob;
    private List<String> mVaccines;

    public Child() {
        this(UUID.randomUUID());
        mDob = new Date();
        mIsTwin = false;
        mIsDisabled = false;
        mMotherNeedsSupport = false;
        mVaccines = new ArrayList<>();
        int i = 0;
    }

    public Child(UUID id) {
        mId = id;
    }


    public boolean isTwin() {
        return mIsTwin;
    }

    public void setTwin(boolean twin) {
        mIsTwin = twin;
    }

    public boolean isDisabled() {
        return mIsDisabled;
    }

    public void setDisabled(boolean disabled) {
        mIsDisabled = disabled;
    }

    public boolean isMotherNeedsSupport() {
        return mMotherNeedsSupport;
    }

    public void setMotherNeedsSupport(boolean motherNeedsSupport) {
        mMotherNeedsSupport = motherNeedsSupport;
    }

    public List<String> getVaccines() {
        return mVaccines;
    }

    public void setVaccines(List<String> vaccines) {
        mVaccines = vaccines;
    }

    public void addVaccine(UUID vaccineId) {
        if (mVaccines == null) {
            mVaccines = new ArrayList<>();
        }
        mVaccines.add(vaccineId.toString());
    }

    public void removeVaccine(UUID vaccineId) {
        mVaccines.remove(vaccineId.toString());
    }

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

    public UUID getMother() {
        return mMother;
    }

    public void setMother(UUID mother) {
        mMother = mother;
    }

    public UUID getFather() {
        return mFather;
    }

    public void setFather(UUID father) {
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
