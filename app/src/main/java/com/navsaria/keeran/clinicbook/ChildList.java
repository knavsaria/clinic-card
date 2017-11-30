package com.navsaria.keeran.clinicbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.navsaria.keeran.clinicbook.database.ChildBaseHelper;
import com.navsaria.keeran.clinicbook.database.ChildCursorWrapper;
import com.navsaria.keeran.clinicbook.database.ChildDbSchema.ChildTable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by keeran on 2017/11/23.
 */

public class ChildList {

    private static ChildList sChildList;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static ChildList getChildList(Context context) {
        if ( sChildList == null) {
            sChildList = new ChildList(context);
        }
        return sChildList;

    }

    private ChildList(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new ChildBaseHelper(context)
                .getWritableDatabase();

    }

    public List<Child> getChildren() {
        List<Child> allChildren = new ArrayList<>();

        ChildCursorWrapper cursor = queryChildren(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                allChildren.add(cursor.getChild());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return  allChildren;
    }

    public void addChild(Child child) {
        ContentValues values = getContentValues(child);
        mDatabase.insert(ChildTable.NAME, null, values);
    }

    public Child getChild(UUID id) {
        ChildCursorWrapper cursor = queryChildren(
                ChildTable.Cols.UUID + " = ?",
                new String[] {id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getChild();
        } finally {
            cursor.close();
        }

    }

    public void updateChild(Child child) {
        String uuidString = child.getId().toString();
        ContentValues values = getContentValues(child);

        mDatabase.update(ChildTable.NAME,values,
                ChildTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }

    private ChildCursorWrapper queryChildren(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                ChildTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new ChildCursorWrapper(cursor);
    }


    private static ContentValues getContentValues(Child child) {
        ContentValues values = new ContentValues();
        values.put(ChildTable.Cols.UUID, child.getId().toString());
        values.put(ChildTable.Cols.FIRST_NAME, child.getFirstName());
        values.put(ChildTable.Cols.SURNAME, child.getLastName());
        if (child.isBoy()) {
            values.put(ChildTable.Cols.GENDER, 1);
        } else {
            values.put(ChildTable.Cols.GENDER, 0);
        }
        values.put(ChildTable.Cols.DATE, child.getDob().getTime());

        return values;
    }
}
