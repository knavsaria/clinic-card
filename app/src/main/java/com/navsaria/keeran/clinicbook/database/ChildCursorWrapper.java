package com.navsaria.keeran.clinicbook.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.navsaria.keeran.clinicbook.Child;
import com.navsaria.keeran.clinicbook.database.ChildDbSchema.ChildTable;

import java.util.Date;
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
        long date = getLong(getColumnIndex(ChildTable.Cols.DATE));
        String id = getString(getColumnIndex(ChildTable.Cols.ID_NUMBER));
        String motherUuid = getString(getColumnIndex(ChildTable.Cols.MOTHER_UUID));
        String fatherUuid = getString(getColumnIndex(ChildTable.Cols.FATHER_UUID));
        String birthFacility = getString(getColumnIndex(ChildTable.Cols.BIRTH_FACILITY));
        String childStayingWith = getString(getColumnIndex(ChildTable.Cols.CHILD_STAYING_WITH));
        String address = getString(getColumnIndex(ChildTable.Cols.ADDRESS));



        Child child = new Child(UUID.fromString(uuidString));
        child.setFirstName(firstName);
        child.setLastName(surname);
        child.setBoy(gender != 0);
        child.setDob(new Date(date));
        child.setIdNumber(id);
        child.setMother(UUID.fromString(motherUuid));
        child.setFather(UUID.fromString(fatherUuid));
        child.setBirthFacility(birthFacility);
        child.setChildStayingWith(childStayingWith);
        child.setAddress(address);

        return child;
    }


}
