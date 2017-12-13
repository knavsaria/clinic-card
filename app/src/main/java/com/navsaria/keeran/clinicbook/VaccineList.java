package com.navsaria.keeran.clinicbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.navsaria.keeran.clinicbook.database.ChildDbSchema;
import com.navsaria.keeran.clinicbook.database.VaccineBaseHelper;
import com.navsaria.keeran.clinicbook.database.VaccineCursorWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.navsaria.keeran.clinicbook.database.ChildDbSchema.*;

/**
 * Created by keeran on 2017/12/08.
 */

public class VaccineList {

    private static VaccineList sVaccineList;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static VaccineList getVaccineList(Context context) {
        if (sVaccineList == null) {
            sVaccineList = new VaccineList(context);
        }
        return sVaccineList;
    }

    private VaccineList (Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new VaccineBaseHelper(context)
                .getWritableDatabase();
    }

    public List<Vaccine> getVaccines() {
        List<Vaccine> allVaccines = new ArrayList<>();

        VaccineCursorWrapper cursor = queryVaccines(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                allVaccines.add(cursor.getVaccine());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return allVaccines;
    }

    public List<Vaccine> getVaccines(List<String> vaccineIds) {
        List<Vaccine> allVaccines = new ArrayList<>();
        for (String vaccineId: vaccineIds) {
            allVaccines.add(getVaccine(UUID.fromString(vaccineId)));
        }
        return allVaccines;
    }

    public void addVaccine(Vaccine vaccine) {
        ContentValues values = getContentValues(vaccine);
        mDatabase.insert(VaccineTable.NAME, null, values);
    }

    public Vaccine getVaccine(UUID id) {
        VaccineCursorWrapper cursor = queryVaccines(
                VaccineTable.Cols.UUID + " = ?",
                new String[] {id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getVaccine();
        } finally {
            cursor.close();
        }
    }

    public void updateVaccine(Vaccine vaccine) {
        String uuidString = vaccine.getId().toString();
        ContentValues values = getContentValues(vaccine);

        mDatabase.update(VaccineTable.NAME, values,
                VaccineTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }

    public void deleteVaccine(UUID vaccineId) {
        mDatabase.delete(VaccineTable.NAME,
                VaccineTable.Cols.UUID + " = ?",
                new String[] {vaccineId.toString()});
    }

    private VaccineCursorWrapper queryVaccines(String whereClause, String[] whereArgs) {
        Cursor cursor= mDatabase.query(
                VaccineTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new VaccineCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Vaccine vaccine) {
        ContentValues values = new ContentValues();
        values.put(VaccineTable.Cols.UUID, vaccine.getId().toString());
        values.put(VaccineTable.Cols.AGE_GROUP, vaccine.getAgeGroup());
        values.put(VaccineTable.Cols.BATCH_NUMBER, vaccine.getBatchNumber());
        values.put(VaccineTable.Cols.VACCINE_CODE, vaccine.getVaccineCode());
        values.put(VaccineTable.Cols.SITE, vaccine.getSite());
        values.put(VaccineTable.Cols.DATE, vaccine.getDateGiven().getTime());

        return values;
    }






} // End of class
