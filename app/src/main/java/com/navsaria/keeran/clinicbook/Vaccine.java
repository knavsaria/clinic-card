package com.navsaria.keeran.clinicbook;

import java.util.Date;
import java.util.UUID;

/**
 * Created by keeran on 2017/12/08.
 */

public class Vaccine {

    private UUID mId;
    private int mAgeGroup;
    private String mBatchNumber;
    private String mVaccineCode;
    private String mSite;
    private Date mDateGiven;

    public Vaccine() {
        this(UUID.randomUUID());
    }

    public Vaccine(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public int getAgeGroup() {
        return mAgeGroup;
    }

    public void setAgeGroup(int ageGroup) {
        mAgeGroup = ageGroup;
    }

    public String getBatchNumber() {
        return mBatchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        mBatchNumber = batchNumber;
    }

    public String getVaccineCode() {
        return mVaccineCode;
    }

    public void setVaccineCode(String vaccineCode) {
        mVaccineCode = vaccineCode;
    }

    public String getSite() {
        return mSite;
    }

    public void setSite(String site) {
        mSite = site;
    }

    public Date getDateGiven() {
        return mDateGiven;
    }

    public void setDateGiven(Date dateGiven) {
        mDateGiven = dateGiven;
    }
}
