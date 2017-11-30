package com.navsaria.keeran.clinicbook.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.navsaria.keeran.clinicbook.Child;
import com.navsaria.keeran.clinicbook.database.ChildDbSchema.ChildTable;

import java.util.UUID;

/**
 * Created by keeran on 2017/11/30.
 */

public class ChildCursorWrapper extends CursorWrapper {

    public ChildCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Child getChild() {
        String uuidString = getString(getColumnIndex(ChildTable.Cols.UUID));
        String firstName = getString(getColumnIndex(ChildTable.Cols.FIRST_NAME));
        String surname = getString(getColumnIndex(ChildTable.Cols.SURNAME));
        int gender = getInt(getColumnIndex(ChildTable.Cols.GENDER));

        Child child = new Child(UUID.fromString(uuidString));
        child.setFirstName(firstName);
        child.setLastName(surname);
        child.setBoy(gender != 0);

        return child;
    }


}
