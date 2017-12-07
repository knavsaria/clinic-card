package com.navsaria.keeran.clinicbook.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.navsaria.keeran.clinicbook.Parent;

import java.util.Date;
import java.util.UUID;

import static com.navsaria.keeran.clinicbook.database.ChildDbSchema.*;

/**
 * Created by keeran on 2017/12/07.
 */

public class ParentCursorWrapper extends CursorWrapper {

    public ParentCursorWrapper(Cursor cursor) {
        super(cursor);
    }


    public Parent getParent() {
        String uuidString = getString(getColumnIndex(ParentTable.Cols.UUID));
        String firstName = getString(getColumnIndex(ParentTable.Cols.FIRST_NAME));
        String surname = getString(getColumnIndex(ParentTable.Cols.SURNAME));
        int gender = getInt(getColumnIndex(ParentTable.Cols.GENDER));
        long date = getLong(getColumnIndex(ParentTable.Cols.DATE));
        String id = getString(getColumnIndex(ParentTable.Cols.ID_NUMBER));
        int noOfBirths = getInt(getColumnIndex(ParentTable.Cols.NO_OF_BIRTHS));
        int noOfAlive= getInt(getColumnIndex(ParentTable.Cols.NO_OF_ALIVE));

        Parent parent = new Parent(UUID.fromString(uuidString));
        parent.setFirstName(firstName);
        parent.setLastName(surname);
        parent.setADad(gender != 0);
        parent.setDob(new Date(date));
        parent.setIdNumber(id);
        parent.setNoOfBirths(noOfBirths);
        parent.setNoOfAlive(noOfAlive);

        return parent;
    }
}
