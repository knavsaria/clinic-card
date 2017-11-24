package com.navsaria.keeran.clinicbook;

import java.util.UUID;

/**
 * Created by keeran on 2017/11/23.
 */

public class Child {

    private UUID mId;
    private String mFirstName;
    private String mLastName;
    private boolean mIsBoy;

    public Child() {
        mId = UUID.randomUUID();
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
