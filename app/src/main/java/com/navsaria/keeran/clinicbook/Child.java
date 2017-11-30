package com.navsaria.keeran.clinicbook;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by keeran on 2017/11/23.
 */

public class Child implements Serializable {

    private UUID mId;
    private String mFirstName;
    private String mLastName;
    private boolean mIsBoy;
    private Date mDob;

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
