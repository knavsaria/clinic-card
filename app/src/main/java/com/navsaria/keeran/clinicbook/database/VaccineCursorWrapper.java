package com.navsaria.keeran.clinicbook.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.navsaria.keeran.clinicbook.Vaccine;

import java.util.Date;
import java.util.UUID;

import static com.navsaria.keeran.clinicbook.database.ChildDbSchema.*;

/**
 * Created by keeran on 2017/12/08.
 */

public class VaccineCursorWrapper extends CursorWrapper {

    public VaccineCursorWrapper(Cursor cursor) {
        super(cursor);
    }


    public Vaccine getVaccine() {
        String uuidString = getString(getColumnIndex(VaccineTable.Cols.UUID));
        int ageGroup = getInt(getColumnIndex(VaccineTable.Cols.AGE_GROUP));
        String batchNumber = getString(getColumnIndex(VaccineTable.Cols.BATCH_NUMBER));
        String vaccineCode = getString(getColumnIndex(VaccineTable.Cols.VACCINE_CODE));
        String site = getString(getColumnIndex(VaccineTable.Cols.SITE));
        long date = getLong(getColumnIndex(VaccineTable.Cols.DATE));

        Vaccine vaccine = new Vaccine(UUID.fromString(uuidString));
        vaccine.setAgeGroup(ageGroup);
        vaccine.setBatchNumber(batchNumber);
        vaccine.setVaccineCode(vaccineCode);
        vaccine.setDateGiven(new Date(date));

        return vaccine;
    }
}
