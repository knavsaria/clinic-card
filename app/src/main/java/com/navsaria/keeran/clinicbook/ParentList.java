package com.navsaria.keeran.clinicbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.navsaria.keeran.clinicbook.database.ChildCursorWrapper;
import com.navsaria.keeran.clinicbook.database.ChildDbSchema;
import com.navsaria.keeran.clinicbook.database.ParentBaseHelper;
import com.navsaria.keeran.clinicbook.database.ParentCursorWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.navsaria.keeran.clinicbook.database.ChildDbSchema.*;

/**
 * Created by keeran on 2017/12/07.
 */

public class ParentList {

    private static ParentList sParentList;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static ParentList getParentList(Context context) {
        if (sParentList == null) {
            sParentList = new ParentList(context);
        }
        return sParentList;
    }

    private ParentList (Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new ParentBaseHelper(context)
                .getWritableDatabase();
    }

    public List<Parent> getParents() {
        List<Parent> allParents = new ArrayList<>();

        ParentCursorWrapper cursor = queryParents(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                allParents.add(cursor.getParent());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return allParents;
    }

    public void addParent(Parent parent) {
        ContentValues values = getContentValues(parent);
        mDatabase.insert(ParentTable.NAME, null, values);
    }

    public Parent getParent(UUID id) {
        ParentCursorWrapper cursor = queryParents(
                ParentTable.Cols.UUID + " = ?",
                new String[] {id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getParent();
        } finally {
            cursor.close();
        }
    }

    public void updateParent(Parent parent) {
        String uuidString = parent.getId().toString();
        ContentValues values = getContentValues(parent);

        mDatabase.update(ParentTable.NAME, values,
                ParentTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }

    private ParentCursorWrapper queryParents(String whereClause, String[] whereArgs) {
        Cursor cursor= mDatabase.query(
                ParentTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new ParentCursorWrapper(cursor);

    }

    private static ContentValues getContentValues(Parent parent) {
        ContentValues values = new ContentValues();
        values.put(ParentTable.Cols.UUID, parent.getId().toString());
        values.put(ParentTable.Cols.FIRST_NAME, parent.getFirstName());
        values.put(ParentTable.Cols.SURNAME, parent.getLastName());
        if (parent.isADad()) {
            values.put(ParentTable.Cols.GENDER, 1);
        } else {
            values.put(ParentTable.Cols.GENDER, 0);
        }
        values.put(ParentTable.Cols.DATE, parent.getDob().getTime());
        values.put(ParentTable.Cols.ID_NUMBER, parent.getIdNumber());
        values.put(ParentTable.Cols.NO_OF_BIRTHS, parent.getNoOfBirths());
        values.put(ParentTable.Cols.NO_OF_ALIVE, parent.getNoOfAlive());

        return values;
    }

}
